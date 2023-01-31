package com.andreesperanca.gymde.ui.login_and_register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.andreesperanca.gymde.databinding.FragmentLoginBinding
import com.andreesperanca.gymde.ui.main.MainActivity
import com.andreesperanca.gymde.utils.extensions.isValidEmail
import com.andreesperanca.gymde.utils.extensions.isValidPassword
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    /** UI COMPONENTS **/
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var btnEnter: Button
    private lateinit var btnCreateAccount: TextView
    private lateinit var pgLogin: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            this@LoginFragment.tilEmail = binding.tilEmail
            this@LoginFragment.tilPassword = binding.tilPassword
            this@LoginFragment.btnEnter = binding.btnEnter
            this@LoginFragment.pgLogin = binding.pgLoginProgressBar
        }

        binding.btnEnter.setOnClickListener {
            if (tilEmail.isValidEmail() &&
                tilPassword.isValidPassword()
            ) {
                tilPassword.editText?.isEnabled = false
                tilEmail.editText?.isEnabled = false
                binding.pgLoginProgressBar.visibility = View.VISIBLE
                /** Fazer login **/
                Handler().postDelayed({
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }, 2000)

            }
        }
    }
}
