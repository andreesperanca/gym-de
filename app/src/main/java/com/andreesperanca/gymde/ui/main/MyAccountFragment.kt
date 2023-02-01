package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentMyAccountBinding
import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.ui.main.viewmodels.MyAccountViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.custom.ConfigureCustomComponent
import com.andreesperanca.gymde.utils.extensions.isVisible
import com.andreesperanca.gymde.utils.extensions.snackBarCreator
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyAccountFragment : BaseFragment<
        FragmentMyAccountBinding,
        MyAccountViewModel>
    (R.layout.fragment_my_account) {

    /** UI COMPONENTS **/
    private lateinit var ivUserPhoto: ImageView
    private lateinit var fabEditPhoto: FloatingActionButton
    private lateinit var cUserName: ConfigureCustomComponent
    private lateinit var cUserEmail: ConfigureCustomComponent
    private lateinit var cUserHeight: ConfigureCustomComponent
    private lateinit var cUserWeight: ConfigureCustomComponent
    private lateinit var progressBarMyAccount: LinearProgressIndicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** FETCH USER **/
        viewModel.fetchUser()

        setClickListeners()

    }

    private fun setClickListeners() {
        fabEditPhoto.setOnClickListener {
            snackBarCreator(getString(R.string.similar_feature_in_exercises) )
        }

        cUserName.setClickEditBtn { snackBarCreator(getString(R.string.similar_feature_update_workouts)) }

    }

    override fun setupToolbar() {
        /** NO HAVE TOOLBAR **/
    }

    override fun setupViewModel() {
        val viewModel: MyAccountViewModel by viewModel()
        this.viewModel = viewModel
    }
    override fun setupObservers() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            when (user) {
                is Resource.Success -> {
                    if(user.data != null) {
                        setupInfo(user.data)
                    }
                    progressBarMyAccount.isVisible(false)
                }
                is Resource.Loading -> {
                    progressBarMyAccount.isVisible(true)
                }
                is Resource.Error -> {
                    snackBarCreator(user.message.toString())
                    progressBarMyAccount.isVisible(false)
                }
            }
        }
    }

    private fun setupInfo(data: User) {
        cUserName.editDescription(data.name.toString())
        cUserEmail.editDescription(data.email.toString())
        cUserHeight.editDescription(data.height.toString())
        cUserWeight.editDescription(data.weight.toString())
    }

    override fun initComponents() {
        ivUserPhoto = binding.ivUserPhoto
        fabEditPhoto = binding.floatingActionButton
        cUserEmail = binding.customUserEmail
        cUserWeight = binding.customUserWeight
        cUserName = binding.customUserName
        cUserHeight = binding.customUserHeight
        progressBarMyAccount = binding.pgMyAccount
    }

}