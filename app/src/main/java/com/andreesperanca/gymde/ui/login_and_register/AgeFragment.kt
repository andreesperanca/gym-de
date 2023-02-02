package com.andreesperanca.gymde.ui.login_and_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.databinding.FragmentAgeBinding
import com.andreesperanca.gymde.utils.extensions.isValidAge
import com.andreesperanca.gymde.utils.extensions.text

class AgeFragment : Fragment() {

    private val args: AgeFragmentArgs by navArgs()

    private val binding by lazy { FragmentAgeBinding.inflate(layoutInflater) }

    /** UI COMPONENTS **/
    private val tbAgeFragment by lazy { binding.tbAgeToolbar }
    private val tilAge by lazy { binding.tilAge }
    private val btnAdvanceAge by lazy { binding.btnAdvanceAge }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdvanceAge.setOnClickListener {
            val newUser = args.newUser
            if (tilAge.isValidAge()) {
                newUser.years = tilAge.text()
                val action = AgeFragmentDirections.actionFocusFragmentToFinishRegister(newUser)
                findNavController().navigate(action)
            }
        }

        tbAgeFragment.apply {
            this.setNavigationOnClickListener { findNavController().popBackStack() }
        }

    }
}