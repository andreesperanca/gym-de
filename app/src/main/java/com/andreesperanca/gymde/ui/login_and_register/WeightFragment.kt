package com.andreesperanca.gymde.ui.login_and_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentWeightBinding
import com.andreesperanca.gymde.utils.extensions.isValidWeight

class WeightFragment : Fragment() {


    val args: HeightFragmentArgs by navArgs()

    private val binding by lazy {
        FragmentWeightBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newUser = args.newUser
        val weight = binding.tilWeight

        binding.btnAdvance.setOnClickListener {
            if (weight.isValidWeight()) {
                newUser.weight = weight.editText?.text.toString()
                val action = WeightFragmentDirections.actionWeightFragmentToAgeFragment(newUser)
                findNavController().navigate(action)
            }
        }

        binding.tbWeightToolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}
