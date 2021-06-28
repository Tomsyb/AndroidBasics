package com.tomsyb.lib_base.ui

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.tomsyb.lib_base.R
import com.tomsyb.lib_base.app.AppManager
import com.tomsyb.lib_base.app.BaseApplication
import com.tomsyb.lib_base.utils.SpHoldHelper
import com.tomsyb.lib_base.utils.UiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Date: 2021/6/28 14:55
 * Author: yanbo
 * Description: 基础界面
 *
 * 1、协程使用注意：
 * a:CoroutineScope by MainScope() 属于kotlin委托模式
 * b:需要的地方直接launch{ } （默认调用器 Dispatchers.Main 无需定义所以可直接launch开启）
 * c:onDestroy 中用cancel清除所有启动的协程
 *
 * 2、使用lifecycleScope启动协程（无需cancel 直接lifecycleScope.launchWhenResumed｛｝）
 * 3、不建议使用GlobalScope.launch启动（会 消耗些内存资源）并且要保持GlobalScope.launch的引用，需要销毁的时候cancel 确保不内存泄露
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    //+++++++++++++++++++++++++++++++++++++++++++++++++ open +++++++++++++++++++++++++++++++++++++++++++++

    /** 此窗口是否需要保密 不能截屏和录屏*/
    open fun isWindowSecure(): Boolean = false
    open fun isFullScreen(): Boolean = false
    open fun isTranslate(): Boolean = false
    open fun translateStatusBar() = false
    open fun bindView(): View? = null

    /** [onCreate] 方法中的savedInstanceState */
    open fun onCreateInstanceData(savedInstanceState: Bundle?) {}
    open fun onObserve() {}

    /** 初始化事件 */
    open fun initEvent() {}

    /** 初始化数据 */
    open fun initData() {}

    //+++++++++++++++++++++++++++++++++++++++++++++++++ abstract +++++++++++++++++++++++++++++++++++++++++++++
    @LayoutRes
    abstract fun bindLayoutId(): Int

    /** 初始化View */
    abstract fun initView()

    //+++++++++++++++++++++++++++++++++++++++++++++++++ 系统方法 +++++++++++++++++++++++++++++++++++++++++++++

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        if (checkErrorLaunch()) return
        initContent()
        onCreateInstanceData(savedInstanceState)
        onObserve()
        initView()
        initEvent()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++ fun +++++++++++++++++++++++++++++++++++++++++++++
    protected fun hideKeyboard() {
        KeyboardUtils.hideSoftInput(this)
    }

    protected fun showKeyboard() {
        KeyboardUtils.showSoftInput(this)
    }

    protected fun intentActivity(router: String) {
        ARouter.getInstance().build(router).navigation()
    }

    protected fun intentActivity(router: String, requestCode: Int) {
        ARouter.getInstance().build(router).navigation(this, requestCode)
    }

    protected fun showToast(toast: String) {
        if (this.isFinishing) return
        ToastUtils.showLong(toast)
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++ private +++++++++++++++++++++++++++++++++++++++++++++

    private fun initContent() {
        val layoutResId = bindLayoutId()
        if (layoutResId != 0) {
            setContentView(layoutResId)
            return
        }
        val contentView = bindView()
        if (contentView != null) {
            setContentView(contentView)
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun changeMode() {
        if (isTranslate()) {
            setTheme(R.style.TranslateActivityTheme)
            return
        }
        val dark = isDark()
        if (translateStatusBar()) {
            BarUtils.transparentStatusBar(this)
        } else {
            BarUtils.setStatusBarColor(this, if (dark) Color.WHITE else Color.BLACK)
        }
        setTheme(if (dark) R.style.AppDarkTheme else R.style.AppLightTheme)
    }

    private fun isDark(): Boolean {
        // 跟随系统 深色模式
        return (SpHoldHelper.darkMode == 0 && UiUtils.isDarkMode(this)) || SpHoldHelper.darkMode == 1
    }


    private fun checkErrorLaunch(): Boolean {
        if (null == AppManager.launcherActivityClass) throw NullPointerException()
        if (this.javaClass == AppManager.launcherActivityClass) return false
        if (BaseApplication.isAppKilled) {
            gotoLauncherActivity()
            return true
        }
        return false
    }

    private fun gotoLauncherActivity() {
        AppManager.clearAll()
        AppManager.gotoLauncherActivity(this)
        LogUtils.e("==> Error launch. this activity name is:${this.javaClass.simpleName}")
        finish()
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++ theme +++++++++++++++++++++++++++++++++++++++++++++

    private fun initTheme() {
        // 竖屏
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        // 防止截屏
        if (isWindowSecure()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
        // 全屏
        if (isFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            }
        }
        // 黑暗模式
        changeMode()
    }
}