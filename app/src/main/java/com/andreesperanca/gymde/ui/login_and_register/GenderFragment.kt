package com.andreesperanca.gymde.ui.login_and_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentGenderBinding
import com.andreesperanca.gymde.models.User

class GenderFragment : Fragment() {

    private val binding by lazy {
        FragmentGenderBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onStart() {

        binding.tbGenderToolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        super.onStart()
        binding.btnAdvanceGender.setOnClickListener {
            val newUser = User()
            if (binding.rgGenderRadioGroup.id == R.id.rb_male) {
                newUser.sex = getString(R.string.male)
            } else {
                newUser.sex = getString(R.string.female)
            }
            val action = GenderFragmentDirections.actionGenderFragmentToHeightFragment(newUser)
            findNavController().navigate(action)
        }
    }
}