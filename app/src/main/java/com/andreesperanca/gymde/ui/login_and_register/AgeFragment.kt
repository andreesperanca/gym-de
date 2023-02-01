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

class AgeFragment : Fragment() {

    val args: AgeFragmentArgs by navArgs()

    private val binding by lazy {
        FragmentAgeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdvanceAge.setOnClickListener {
            val age = binding.tilHeight
            val newUser = args.newUser
            if ( age.isValidAge()) {
                newUser.years = binding.tilHeight.editText?.text.toString()
                val action = AgeFragmentDirections.actionFocusFragmentToFinishRegister(newUser)
                findNavController().navigate(action)
            }
        }

    }
}