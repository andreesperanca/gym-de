package com.andreesperanca.gymde.ui.login_and_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentGenderBinding

class GenderFragment : Fragment() {

    private val binding by lazy {
        FragmentGenderBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onStart() {
        super.onStart()
        binding.btnAdvance.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_heightFragment)
        }
    }
}