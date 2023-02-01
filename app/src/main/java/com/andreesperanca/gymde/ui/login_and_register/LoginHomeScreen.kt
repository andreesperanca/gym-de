package com.andreesperanca.gymde.ui.login_and_register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentLoginHomeScreenBinding
import com.andreesperanca.gymde.ui.login_and_register.viewmodels.LoginAndRegisterViewModel
import com.andreesperanca.gymde.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginHomeScreen : Fragment() {

    private val binding by lazy {
        FragmentLoginHomeScreenBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginAndRegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = viewModel.getUserLiveData()
        if (user != null) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        setupButtons()
    }
    private fun setupButtons() {
        binding.btnEnter.setOnClickListener {
            findNavController().navigate(R.id.action_loginHomeScreen_to_loginFragment)
        }

        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginHomeScreen_to_genderFragment)
        }
    }
}