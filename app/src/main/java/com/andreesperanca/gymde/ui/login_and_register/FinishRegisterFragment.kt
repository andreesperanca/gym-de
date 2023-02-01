package com.andreesperanca.gymde.ui.login_and_register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentFinishRegisterBinding
import com.andreesperanca.gymde.ui.login_and_register.viewmodels.LoginAndRegisterViewModel
import com.andreesperanca.gymde.ui.main.MainActivity
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.disableComponents
import com.andreesperanca.gymde.utils.extensions.*
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishRegisterFragment : BaseFragment<
        FragmentFinishRegisterBinding,
        LoginAndRegisterViewModel>(
    R.layout.fragment_finish_register
) {
    private val args: HeightFragmentArgs by navArgs()

    /** UI COMPONENTS **/
    private lateinit var tbFinishRegister: Toolbar
    private lateinit var _tilName: TextInputLayout
    private lateinit var _tilEmail: TextInputLayout
    private lateinit var _tilPassword: TextInputLayout
    private lateinit var _tilPasswordConfirm: TextInputLayout
    private lateinit var _btnAdvanceFinishRegister: Button
    private lateinit var pgFinishRegister: LinearProgressIndicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {

        _btnAdvanceFinishRegister.setOnClickListener {
            if (_tilName.isValidName() &&
                _tilEmail.isValidEmail() &&
                _tilPassword.isValidPassword() &&
                _tilPasswordConfirm.isValidConfirmPassword(_tilPassword.text())
            ) {
                val newUser = args.newUser
                newUser.email = _tilEmail.text()
                newUser.name = _tilName.text()

                viewModel.createUser(
                    sex = newUser.sex.toString(),
                    height = newUser.height.toString(),
                    weight = newUser.weight.toString(),
                    age = newUser.years.toString(),
                    name = newUser.name.toString(),
                    email = newUser.email.toString(),
                    password = _tilPassword.text()
                )
            }
        }

        tbFinishRegister.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun setupToolbar() {
        /** NO HAVA TOOLBAR **/
    }

    override fun setupViewModel() {
        val viewModel: LoginAndRegisterViewModel by viewModel()
        this.viewModel = viewModel
    }

    override fun setupObservers() {
        viewModel.userRegistrationStatus.observe(viewLifecycleOwner) { register ->
            when (register) {
                is Resource.Success -> {
                    pgFinishRegister.isVisible(false)
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                is Resource.Loading -> {
                    pgFinishRegister.isVisible(true)
                    disableComponents(
                        listOf(_tilEmail, _tilName, _tilPassword, _tilPasswordConfirm)
                    )

                }
                is Resource.Error -> {
                    snackBarCreator(register.message.toString())
                    pgFinishRegister.isVisible(false)
                }
            }
        }
    }

    override fun initComponents() {
        with(binding) {
            tbFinishRegister = tbFinishRegisterToolbar
            pgFinishRegister = pgProgressBarFinishRegister
            _tilPassword = tilPassword
            _tilName = tilName
            _tilPasswordConfirm = tilPasswordConfirm
            _tilEmail = tilEmail
            _btnAdvanceFinishRegister = btnAdvanceFinishRegister

        }
    }
}