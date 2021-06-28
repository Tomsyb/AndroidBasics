package com.tomsyb.lib_base.ext


/**
 * Author:      HW
 * Date:        19-11-12 上午10:07
 * Description:
 */

fun String?.isNullOrEmptyOrBlank(): Boolean {
    return this.isNullOrEmpty() || this.isNullOrBlank()
}