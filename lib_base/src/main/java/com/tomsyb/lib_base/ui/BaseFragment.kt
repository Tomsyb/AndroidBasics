package com.tomsyb.lib_base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Date: 2021/6/28 17:04
 * Author: yanbo
 * Description:
 */
abstract class BaseFragment : Fragment() , CoroutineScope by MainScope() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserve()
        onCreatedSavedInstanceState(savedInstanceState)
        initView()
        initEvent()
        initData()
    }

    override fun onDestroyView() {
        cancel()
        super.onDestroyView()
    }

    abstract fun layoutId(): Int
    open fun onObserve() {}
    open fun onCreatedSavedInstanceState(savedInstanceState: Bundle?) {}
    abstract fun initView()
    open fun initData() {}
    open fun initEvent() {}
}