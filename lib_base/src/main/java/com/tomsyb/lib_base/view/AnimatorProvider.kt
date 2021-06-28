package com.tomsyb.lib_base.view

import android.animation.Animator
import android.view.View

/**
 * Author:      yanbo
 * Date:        2021-04-30
 * Description: 状态view动画提供
 */
interface AnimatorProvider {
    fun showAnimation(view: View): Animator?

    fun hideAnimation(view: View): Animator?
}