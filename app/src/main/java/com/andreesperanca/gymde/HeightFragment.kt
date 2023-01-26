package com.andreesperanca.gymde

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.databinding.FragmentHeightBinding

class HeightFragment : Fragment() {

    private val binding by lazy {
        FragmentHeightBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdvance.setOnClickListener {
            findNavController().navigate(R.id.action_heightFragment_to_weightFragment)
        }
    }
}