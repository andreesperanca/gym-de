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
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentLoginBinding
import com.andreesperanca.gymde.ui.login_and_register.viewmodels.LoginAndRegisterViewModel
import com.andreesperanca.gymde.ui.main.MainActivity
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.disableComponents
import com.andreesperanca.gymde.utils.enableComponents
import com.andreesperanca.gymde.utils.extensions.*
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment() : BaseFragment<
        FragmentLoginBinding,
        LoginAndRegisterViewModel>(R.layout.fragment_login) {

    /** UI COMPONENTS **/
    private val _tilEmail by lazy { binding.tilEmail }
    private val _tilPassword by lazy { binding.tilPassword }
    private val _btnEnter by lazy { binding.btnEnter }
    private val _pgLogin by lazy { binding.pgLoginProgressBar }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _btnEnter.setOnClickListener {
            val email = _tilEmail.text()
            val password = _tilPassword.text()
            if (_tilEmail.isValidEmail() && _tilPassword.isValidPassword()) {
                viewModel.login(email, password)
            }
        }
    }
    override fun setupToolbar() {
        /** NO HAVE TOOLBAR **/
    }
    override fun setupViewModel() {
        val loginAndRegisterViewModel: LoginAndRegisterViewModel by sharedViewModel()
        this.viewModel = loginAndRegisterViewModel
    }
    override fun setupObservers() {
        viewModel.login.observe(viewLifecycleOwner) { login ->
            when (login) {
                is Resource.Success -> {
                    _pgLogin.isVisible(false)
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                is Resource.Loading -> {
                    _pgLogin.isVisible(true)
                    disableComponents(listOf(_tilEmail, _tilPassword))
                }
                is Resource.Error -> {
                    snackBarCreator(login.message.toString())
                    _pgLogin.isVisible(false)
                    enableComponents(listOf(_tilEmail, _tilPassword))
                }
            }
        }
    }
}
