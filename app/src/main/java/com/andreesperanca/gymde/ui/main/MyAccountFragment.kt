package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreesperanca.gymde.databinding.FragmentMyAccountBinding


class MyAccountFragment : Fragment() {

    private val binding by lazy {
        FragmentMyAccountBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}