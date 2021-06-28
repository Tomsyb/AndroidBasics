package com.tomsyb.lib_base.utils


/**
 * 这里的数据全程保留，退出登录不会删除
 */
object SpHoldHelper {

    // 深色模式。0：跟随系统，1：深色模式，2：浅色模式
    var darkMode: Int
        get() {
            return MmkvUtil.get("apply_dark_mode", 0)
        }
        set(value) {
            MmkvUtil.put("apply_dark_mode", value)
        }

}