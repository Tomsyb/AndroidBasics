package com.tomsyb.lib_base.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



fun EditText.content(): String = if (this.text.isNullOrEmpty()) "" else this.text.toString().trim()

fun View.ofAlphaAnim(start: Float = 0.2F, end: Float = 1F, duration: Long = 500, onEnd: (() -> Unit)? = null) {
    val anim = ObjectAnimator.ofFloat(this, "alpha", start, end)
    anim.duration = duration
    anim.start()
    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            onEnd?.invoke()
        }
    })
}

fun RecyclerView.closeAnimator() {
    val animator = this.itemAnimator
    if (null != animator && animator is DefaultItemAnimator) {
        animator.run {
            supportsChangeAnimations = false
            addDuration = 0
            changeDuration = 0
            moveDuration = 0
            removeDuration = 0
            moveDuration = 0
        }
    }
}

fun RecyclerView.openAnimator() {
    val animator = this.itemAnimator
    if (null != animator && animator is DefaultItemAnimator) {
        animator.run {
            supportsChangeAnimations = true
            addDuration = 120
            changeDuration = 250
            moveDuration = 250
            removeDuration = 120
            moveDuration = 250
        }
    }
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

inline var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

inline var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

inline fun <reified T : View> View.find(@IdRes id: Int): T = findViewById(id)

inline fun <reified T : View> RecyclerView.ViewHolder.find(@IdRes id: Int): T = this.itemView.findViewById(id)


// 扩展点击事件属性
var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

/**
 * recyclerView 链式操作
 */
fun RecyclerView.layoutManager(layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)): RecyclerView {
    this.layoutManager = layoutManager
    return this
}

fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>, hasStatleIds: Boolean = true) {
    this.adapter = adapter
}

fun RecyclerView.gridLayoutManager(spanCount: Int = 4): RecyclerView {
    this.layoutManager = GridLayoutManager(this.context, spanCount)
    return this
}
