package com.andreesperanca.gymde

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreesperanca.gymde.databinding.FragmentWeightBinding

class WeightFragment : Fragment() {

    private val binding by lazy {
        FragmentWeightBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root
}