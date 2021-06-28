package com.tomsyb.lib_base.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.widget.Checkable
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bat.base.R
import com.bat.base.imp.ForbidAccessibilityDelegate
import com.bat.logger.LogUtil
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter


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

fun SmartRefreshLayout.setHeaderForMaterial(context: Context) {
    this.setRefreshHeader(MaterialHeader(context))
    this.setRefreshFooter(ClassicsFooter(context))
}

fun SwipeRefreshLayout.setColorAndListener(refreshListener: () -> Unit) {
    this.setColorSchemeResources(R.color.color_007FFA)
    this.setOnRefreshListener(refreshListener)
}

/**
 * 点击事件
 */
inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    this.forbidSimulateClick()
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}

inline fun <T : View> T.doubleClick(crossinline block: (T) -> Unit) {
    this.forbidSimulateClick()
    var lastClickTimeStamp = 0L
    var lastDoubleClickTimeStamp = 0L
    setOnClickListener {
        val now = System.currentTimeMillis()
        // 上次的点击时间如果为初始值，那么肯定是第一次点击，先赋值为现在的时间
        if (lastClickTimeStamp == 0L) {
            lastClickTimeStamp = now
            LogUtil.d("==> 第一次点击")
            return@setOnClickListener
        }
        // 如果上次双击的时间为初始值，肯定是第一次双击，直接赋值为现在的时间值，并且不拦截
        if (lastDoubleClickTimeStamp == 0L) {
            lastDoubleClickTimeStamp = now
        } else {
            // 如果上次双击的时间和现在双击的时间相距不足1秒，直接拦截
            if (now - lastDoubleClickTimeStamp <= 1_000) {
                lastClickTimeStamp = now
                LogUtil.d("==> 距离上次双击时间太近")
                return@setOnClickListener
            }
        }
        if (now - lastClickTimeStamp <= 1000) {
            block.invoke(this)
            lastDoubleClickTimeStamp = now
        } else {
            LogUtil.d("==> 双击时间相距较大")
        }
        lastClickTimeStamp = now
    }
}

//兼容点击事件设置为this的情况
fun <T : View> T.singleClick(onClickListener: View.OnClickListener?, time: Long = 800) {
    this.forbidSimulateClick()
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            onClickListener?.onClick(this)
        }
    }
}

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

/**
 * recyclerView 链式操作
 * FlexboxLayoutManager
 */
fun RecyclerView.flexboxLayoutManager(layoutManager: FlexboxLayoutManager = FlexboxLayoutManager(this.context)): RecyclerView {
    layoutManager.apply {
        flexDirection = FlexDirection.ROW
        flexWrap = FlexWrap.WRAP
        alignItems = AlignItems.STRETCH
    }
    this.layoutManager = layoutManager
    this.clipToPadding = false
    return this
}


fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>, hasStatleIds: Boolean = true) {
    this.adapter = adapter
}

fun RecyclerView.gridLayoutManager(spanCount: Int = 4): RecyclerView {
    this.layoutManager = GridLayoutManager(this.context, spanCount)
    return this
}

fun View?.forbidSimulateClick() {
    if (null == this) return
    this.accessibilityDelegate = ForbidAccessibilityDelegate()
}
