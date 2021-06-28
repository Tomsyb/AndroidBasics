package com.tomsyb.lib_base.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.BuildConfig
import com.tomsyb.lib_base.utils.MmkvUtil

/**
 * Date: 2021/6/28 15:31
 * Author: yanbo
 * Description:
 */
class BaseApplication : Application() {
    companion object {
        lateinit var App: BaseApplication
            private set
        /* 只能在启动界面把它置为false，用来标记是否是错误启动APP */
        var isAppKilled = true
    }

    override fun onCreate() {
        App = this
        super.onCreate()
        initializeLib()
    }

    /**
     * 初始化一些库或者工具类
     * NOTE：有可能会存在相互引用，所以不要随意更改初始化顺序
     */
    private fun initializeLib() {
        // Mmkv本地储存
        MmkvUtil.initialize(this, BuildConfig.DEBUG)
        Utils.init(this)
        ARouter.init(this)
    }
}