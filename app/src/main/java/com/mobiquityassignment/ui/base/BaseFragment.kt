package com.mobiquityassignment.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected lateinit var dataBinding: VB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false)
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        setViews()
        return dataBinding.root
    }

    abstract fun setLayout(): Int

    abstract fun setViews()

    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
    }
}