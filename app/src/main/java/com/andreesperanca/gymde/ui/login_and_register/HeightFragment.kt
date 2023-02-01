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
import com.andreesperanca.gymde.utils.extensions.text

class HeightFragment : Fragment() {

    private val args: HeightFragmentArgs by navArgs()

    private val binding by lazy { FragmentHeightBinding.inflate(layoutInflater) }

    /** UI COMPONENTS **/
    private val heightToolbar by lazy { binding.tbHeightToolbar }
    private val btnAdvanceHeight by lazy { binding.btnAdvanceHeight }
    private val tilHeight by lazy { binding.tilHeight }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        heightToolbar.apply {
            this.setNavigationOnClickListener { findNavController().popBackStack() }
        }

        btnAdvanceHeight.setOnClickListener {
            val newUser = args.newUser
            if (tilHeight.isValidHeight()) {
                newUser.height = tilHeight.text()
                val action = HeightFragmentDirections.actionHeightFragmentToWeightFragment(newUser)
                findNavController().navigate(action)
            }
        }
    }
}