package com.andreesperanca.gymde.utils.generics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<
        T : ViewDataBinding,
        V : ViewModel>(
    @LayoutRes private val layoutId: Int

) : Fragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = (DataBindingUtil
        .inflate(inflater, layoutId, container, false) as T)
        .apply {
            lifecycleOwner = viewLifecycleOwner
            binding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        setupToolbar()
        setupObservers()
    }

    abstract fun setupToolbar()
    abstract fun setupViewModel()
    abstract fun setupObservers()
    abstract fun initComponents()
}