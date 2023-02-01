package com.andreesperanca.gymde.ui.login_and_register

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishRegisterFragment : BaseFragment<
        FragmentFinishRegisterBinding,
        LoginAndRegisterViewModel>(
    R.layout.fragment_finish_register
) {
    private val args: HeightFragmentArgs by navArgs()

    /** UI COMPONENTS **/
    private val tbFinishRegister by lazy { binding.tbFinishRegisterToolbar }
    private val tilName by lazy { binding.tilName }
    private val tilEmail by lazy { binding.tilEmail }
    private val tilPassword by lazy { binding.tilPassword }
    private val tilPasswordConfirm by lazy { binding.tilPasswordConfirm }
    private val btnAdvanceFinishRegister by lazy { binding.btnAdvanceFinishRegister }
    private val pgFinishRegister by lazy { binding.pgProgressBarFinishRegister }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        btnAdvanceFinishRegister.setOnClickListener {
            if (tilName.isValidName() &&
                tilEmail.isValidEmail() &&
                tilPassword.isValidPassword() &&
                tilPasswordConfirm.isValidConfirmPassword(tilPassword.text())
            ) {
                val newUser = args.newUser
                newUser.email = tilEmail.text()
                newUser.name = tilName.text()

                viewModel.createUser(
                    sex = newUser.sex,
                    height = newUser.height,
                    weight = newUser.weight,
                    age = newUser.years,
                    name = newUser.name,
                    email = newUser.email,
                    password = tilPassword.text()
                )
            }
        }

        tbFinishRegister.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun setupToolbar() { /** NO HAVA TOOLBAR **/ }
    override fun setupViewModel() {
        val viewModel : LoginAndRegisterViewModel by viewModel()
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
                        listOf(tilEmail, tilName, tilPassword, tilPasswordConfirm))

                }
                is Resource.Error -> {
                    snackBarCreator(register.message.toString())
                    pgFinishRegister.isVisible(false)
                }
            }
        }
    }
}