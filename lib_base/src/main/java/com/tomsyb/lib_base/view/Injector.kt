package com.tomsyb.lib_base.view

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingParent
import androidx.core.view.ScrollingView
import com.blankj.utilcode.util.LogUtils

/**
 * Author:      yanbo
 * Date:        2021-04-30
 * Description: 状态view注解
 */
internal object Injector {

    val constraintLayoutAvailable = try {
        null != Class.forName("androidx.constraintlayout.widget.ConstraintLayout")
    } catch (e: Throwable) {
        false
    }

    /**
     * Create a new FrameLayout (wrapper), let parent's remove children views, and add to the wrapper,
     * stateVew add to wrapper, wrapper add to parent
     *
     * @param parent target
     * @return [StateView]
     */
    fun wrapChild(parent: ViewGroup): StateView {
        // If there are other complex needs, maybe you can use stateView in layout(.xml)
        var screenHeight = 0
        // create a new FrameLayout to wrap StateView and parent's childView
        val wrapper = FrameLayout(parent.context)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        wrapper.layoutParams = layoutParams

        if (parent is LinearLayout) {
            // create a new LinearLayout to wrap parent's childView
            val wrapLayout = LinearLayout(parent.context)
            wrapLayout.layoutParams = parent.layoutParams ?: layoutParams
            wrapLayout.orientation = parent.orientation
            var i = 0
            val childCount: Int = parent.getChildCount()
            while (i < childCount) {
                val childView: View = parent.getChildAt(0)
                parent.removeView(childView)
                wrapLayout.addView(childView)
                i++
            }
            wrapper.addView(wrapLayout)
        } else if (parent is ScrollView || parent is ScrollingView) {
            // not recommended to inject Scrollview/NestedScrollView
            if (parent.childCount != 1) {
                throw IllegalStateException("the ScrollView does not have one direct child")
            }
            val directView = parent.getChildAt(0)
            parent.removeView(directView)
            wrapper.addView(directView)
            val wm = parent.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(metrics)
            screenHeight = metrics.heightPixels
        } else if (parent is NestedScrollingParent && parent is NestedScrollingChild) {
            if (parent.childCount == 2) {
                val targetView = parent.getChildAt(1)
                parent.removeView(targetView)
                wrapper.addView(targetView)
            } else if (parent.childCount > 2) {
                throw IllegalStateException("the view is not refresh layout? view = $parent")
            }
        } else {
            throw IllegalStateException("the view does not have parent, view = $parent")
        }
        // parent add wrapper
        parent.addView(wrapper)
        // StateView will be added to wrapper
        val stateView = StateView(parent.context)
        if (screenHeight > 0) {
            // let StateView be shown in the center
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight)
            wrapper.addView(stateView, params)
        } else {
            wrapper.addView(stateView)
        }
        return stateView
    }

    /**
     * Set StateView to be the same size and position as the target view，
     * layoutParams should be use [ConstraintLayout.LayoutParams] (android.view.ViewGroup.LayoutParams)
     *
     * @param parent view's parent, [ConstraintLayout]
     * @param view target view
     * @return [StateView]
     */
    fun matchViewIfParentIsConstraintLayout(parent: ConstraintLayout, view: View): StateView {
        val stateView = StateView(parent.context)
        val lp = ConstraintLayout.LayoutParams(view.layoutParams as ViewGroup.LayoutParams)
        lp.leftToLeft = view.id
        lp.rightToRight = view.id
        lp.topToTop = view.id
        lp.bottomToBottom = view.id
        parent.addView(stateView, lp)
        return stateView
    }

    /**
     * Set StateView to be the same size and position as the target view， If parent is RelativeLayout
     *
     * @param parent view's parent, [RelativeLayout]
     * @param view target view
     * @return [StateView]
     */
    fun matchViewIfParentIsRelativeLayout(parent: RelativeLayout, view: View): StateView {
        val stateView = StateView(parent.context)
        val lp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams(view.layoutParams as RelativeLayout.LayoutParams)
        } else {
            RelativeLayout.LayoutParams(view.layoutParams)
        }
        parent.addView(stateView, lp)
        setStateListAnimator(stateView, view)
        return stateView
    }

    /**
     * In order to display on the button top
     */
    fun setStateListAnimator(stateView: StateView, target: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && target is Button) {
            LogUtils.i("for normal display, stateView.stateListAnimator = view.stateListAnimator")
            stateView.stateListAnimator = target.stateListAnimator
        }
    }
}