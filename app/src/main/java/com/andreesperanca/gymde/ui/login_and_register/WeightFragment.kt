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
import com.andreesperanca.gymde.utils.extensions.text

class WeightFragment : Fragment() {


    private val args: HeightFragmentArgs by navArgs()

    private val binding by lazy { FragmentWeightBinding.inflate(layoutInflater) }

    /** UI COMPONENTS **/
    private val weightToolbar by lazy { binding.tbWeightToolbar }
    private val btnAdvanceWeight by lazy { binding.btnAdvanceWeight }
    private val tilWeight by lazy { binding.tilWeight }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newUser = args.newUser
        btnAdvanceWeight.setOnClickListener {
            if (tilWeight.isValidWeight()) {
                newUser.weight = tilWeight.text()
                val action = WeightFragmentDirections.actionWeightFragmentToAgeFragment(newUser)
                findNavController().navigate(action)
            }
        }

        weightToolbar.apply {
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }
}
