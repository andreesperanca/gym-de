package com.andreesperanca.gymde

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreesperanca.gymde.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}