package com.andreesperanca.gymde.ui.login_and_register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.databinding.FragmentFinishRegisterBinding
import com.andreesperanca.gymde.ui.login_and_register.viewmodels.LoginAndRegisterViewModel
import com.andreesperanca.gymde.ui.main.MainActivity
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishRegisterFragment : Fragment() {

    val args: HeightFragmentArgs by navArgs()

    private val binding by lazy {
        FragmentFinishRegisterBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginAndRegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdvanceFinishRegister.setOnClickListener {
            val email = binding.tilEmail
            val password = binding.tilPassword
            val confirmPassword = binding.tilPasswordConfirm
            val name = binding.tilName

            val tPassword = password.editText?.text.toString()

            if (name.isValidName() &&
                email.isValidEmail() &&
                password.isValidPassword() &&
                confirmPassword.isValidConfirmPassword(tPassword)
            ) {

                val newUser = args.newUser
                newUser.email = email.editText?.text.toString()
                newUser.name = name.editText?.text.toString()

                viewModel.createUser(
                    sex = newUser.sex,
                    height = newUser.height,
                    weight = newUser.weight,
                    age = newUser.years,
                    name = newUser.name,
                    email = newUser.email,
                    password = tPassword
                )
            }
        }

        binding.tbFinishRegisterToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.userRegistrationStatus.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    binding.pgProgressBarFinishRegister.visibility = View.INVISIBLE
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                is Resource.Loading -> {
                    binding.tilEmail.isEnabled = false
                    binding.tilName.isEnabled = false
                    binding.tilPassword.isEnabled = false
                    binding.tilPasswordConfirm.isEnabled = false
                    binding.pgProgressBarFinishRegister.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    toastCreator(it.message.toString())
                    binding.pgProgressBarFinishRegister.visibility = View.INVISIBLE
                }
            }
        }

    }

}