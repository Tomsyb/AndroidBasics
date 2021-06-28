package com.tomsyb.lib_base.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View

/**
 * Author:      yanbo
 * Date:        2021-04-30
 * Description: 状态view逐渐显示和消失动画
 */
class FadeAlphaAnimatorProvider : AnimatorProvider {

    override fun showAnimation(view: View): Animator? {
        return ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f).apply { duration = 10L }
    }

    override fun hideAnimation(view: View): Animator? {
        return ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f).apply { duration = 250L }
    }


}