package com.tomsyb.lib_base.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Process
import com.blankj.utilcode.util.LogUtils
import com.tomsyb.lib_base.ext.isNullOrEmptyOrBlank
import com.tomsyb.lib_base.utils.Router
import java.util.*
import kotlin.system.exitProcess

object AppManager {
    private val activities: Stack<Activity> = Stack()

    /**
     * 进过多次测试发现，当APP进入后台时，当设备回收掉进程之后，从“最近”任务栏中启动APP，会出现直接进入MainActivity或者离开时的最后一个页面，而不会从启动页面进入
     * 很多数据没有初始化和登录等操作，导致APP各种异常。
     * 经过测试发现，这种情况是系统会直接启动Activity任务栈的最后一个Activity
     * 所以我们简单的处理方式是：只有在登录和注册等少数几个界面按返回键时才销毁启动页，其余任何时候都不销毁启动
     */
    var launcherActivityClass: Class<out Activity>? = null
    var mainActivityClass: Class<out Activity>? = null
    /** 密聊主页 */
    var secretMainActivityClass: Class<out Activity>? = null

    fun getActivityCount(): Int {
        return activities.size
    }

    fun add(activity: Activity) {
        synchronized(activities) {
            activities.add(activity)
        }
    }

    fun remove(activity: Activity) {
        synchronized(activities) {
            activities.remove(activity)
        }
    }

    fun top(): Activity = activities.lastElement()

    fun clearKeepLauncherActivity() {
        synchronized(activities) {
            val iterator = activities.iterator()
            while (iterator.hasNext()) {
                val ac = iterator.next() ?: continue
                if (ac.javaClass != launcherActivityClass) {
                    ac.finish()
                    iterator.remove()
                }
            }
        }
    }

    /**
     * 只保留主页和密聊主页
     */
    fun clearKeepMainActivity() {
        synchronized(activities) {
            val iterator = activities.iterator()
            while (iterator.hasNext()) {
                val ac = iterator.next() ?: continue
                if (ac.javaClass != mainActivityClass && ac.javaClass != secretMainActivityClass) {
                    ac.finish()
                    iterator.remove()
                }
            }
        }
    }

    /**
     * 只保留主页 和指定页面
     */
    fun clearKeepMainWithThisActivity(thisAc: Class<out Activity>) {
        synchronized(activities) {
            val iterator = activities.iterator()
            while (iterator.hasNext()) {
                val ac = iterator.next() ?: continue
                if (ac.javaClass != mainActivityClass && ac.javaClass != thisAc) {
                    ac.finish()
                    iterator.remove()
                }
            }
        }
    }

    fun clearKeepMain(){
        synchronized(activities) {
            val iterator = activities.iterator()
            while (iterator.hasNext()) {
                val ac = iterator.next() ?: continue
                if (ac.javaClass != mainActivityClass) {
                    ac.finish()
                    iterator.remove()
                }
            }
        }
    }

    /**
     * 关闭指定界面
     */
    fun finishTagActivities(vararg act: Class<out Activity>) {
        synchronized(activities) {
            val iterator = activities.iterator()
            while (iterator.hasNext()) {
                val ac = iterator.next() ?: continue
                act.forEach {
                    if (ac.javaClass == it) {
                        ac.finish()
                        iterator.remove()
                    }
                }
            }
        }
    }



    /**
     * 转移群主调用
     */
    fun clearKeepMainTurnGroupRoot(thisAc: Class<out Activity>,chatAc:Class<out Activity>) {
        synchronized(activities) {
            val iterator = activities.iterator()
            while (iterator.hasNext()) {
                val ac = iterator.next() ?: continue
                if (ac.javaClass != mainActivityClass && ac.javaClass != thisAc&&ac.javaClass != chatAc) {
                    ac.finish()
                    iterator.remove()
                }
            }
        }
    }

    fun mainActivityExist(): Boolean {
        synchronized(activities) {
            if (null == mainActivityClass) return false
            for (ac in activities) {
                if (ac.javaClass == mainActivityClass) return true
            }
            return false
        }
    }

    fun clearAll() {
        synchronized(activities) {
            activities.forEach { it.finish() }
            activities.clear()
        }
    }

    fun finishLauncherActivity() {
        synchronized(activities) {
            activities.firstOrNull { it.javaClass == launcherActivityClass }?.finish()
        }
    }

    fun gotoLauncherActivity(context: Context): Boolean {
        if (null == launcherActivityClass) return false
        context.startActivity(Intent(context, launcherActivityClass))
        return true
    }

    fun printStackContent() {
        synchronized(activities) {
            activities.forEach {
                LogUtils.i("==> Activity Stack: ${it.javaClass.simpleName}")
            }
        }
    }

    fun getMainActivity():Activity?{
        if(mainActivityClass==null) return null
        return findActivity(mainActivityClass!!)
    }

    fun activityExist(actRouterPath:String):Boolean{
        val name = Router.obtainClassName(actRouterPath)
        if (name.isNullOrEmptyOrBlank()) return false
        return getActivityStack().map { it.localClassName }.contains(name)
    }

    fun activityExist(activityClz: Class<out Activity>): Boolean {
        return findActivity(activityClz) != null
    }

    fun <T : Activity> findActivity(activityClz: Class<T>): T? {
        synchronized(activities) {
            val act = activities.firstOrNull { it.javaClass == activityClz } ?: return null
            return act as T
        }
    }

    fun countActivity(activityClz: Class<out Activity>): Int {
        synchronized(activities) {
            return activities.asSequence().filter { it.javaClass == activityClz }.count()
        }
    }

    fun getActivityStack(): Stack<Activity> {
        return activities
    }

    fun getTopActivity(): Activity? {
        synchronized(activities) {
            return if (activities.size >= 1) activities[activities.size - 1] else null
        }
    }

    fun killProcess() {
        try {
            clearAll()
            Process.killProcess(Process.myPid())
            exitProcess(0) // 0:正常退出  1：非正常退出
        } catch (ignore: Exception) {
            ignore.printStackTrace()
        }
    }

}