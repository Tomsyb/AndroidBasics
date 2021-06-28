package com.tomsyb.lib_base.utils

import android.content.Context
import android.content.res.Configuration

/**
 * Date: 2021/6/28 15:40
 * Author: yanbo
 * Description:
 */
object UiUtils {
    /** 判断是否是黑暗模式 */
    fun isDarkMode(context: Context): Boolean {
        return Configuration.UI_MODE_NIGHT_YES == context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    }
}