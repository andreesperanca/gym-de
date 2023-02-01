package com.andreesperanca.gymde.ui.login_and_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.databinding.FragmentHeightBinding
import com.andreesperanca.gymde.utils.extensions.isValidHeight

class HeightFragment : Fragment() {

    private val args: HeightFragmentArgs by navArgs()

    private val binding by lazy { FragmentHeightBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdvanceHeight.setOnClickListener {
            val newUser = args.newUser
            if (binding.tilHeight.isValidHeight()) {
                newUser.height = binding.tilHeight.editText?.text.toString()
                val action = HeightFragmentDirections.actionHeightFragmentToWeightFragment(newUser)
                findNavController().navigate(action)
            }
        }

    }
}