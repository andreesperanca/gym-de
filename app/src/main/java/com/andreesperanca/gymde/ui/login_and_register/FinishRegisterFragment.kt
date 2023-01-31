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
import com.andreesperanca.gymde.ui.main.MainActivity
import com.andreesperanca.gymde.utils.extensions.*

class FinishRegisterFragment : Fragment() {

    val args: HeightFragmentArgs by navArgs()

    private val binding by lazy {
        FragmentFinishRegisterBinding.inflate(layoutInflater)
    }

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
                newUser.name = email.editText?.text.toString()

                /** Register account and login **/
                email.isEnabled = false
                password.isEnabled = false
                confirmPassword.isEnabled = false
                name.isEnabled = false

                binding.pgProgressBarFinishRegister.visibility = View.VISIBLE

                Handler().postDelayed({
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }, 3000)


            }
        }

        binding.tbFinishRegisterToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

}