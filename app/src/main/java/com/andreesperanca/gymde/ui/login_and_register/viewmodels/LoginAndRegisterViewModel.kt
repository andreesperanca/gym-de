package com.andreesperanca.gymde.ui.login_and_register.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.repositories.LoginAndRegisterRepository
import com.andreesperanca.gymde.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginAndRegisterViewModel(
    private val repository: LoginAndRegisterRepository
) : ViewModel() {

    private val _userRegistrationStatus = MutableLiveData<Resource<AuthResult>>()
    val userRegistrationStatus: LiveData<Resource<AuthResult>> = _userRegistrationStatus

    private val _loggedUser = MutableLiveData<Resource<FirebaseUser?>>()
    val loggedUser: LiveData<Resource<FirebaseUser?>> = _loggedUser

    fun getUserLiveData() = repository.getUser()

    fun createUser(
        sex: String,
        height: String,
        weight: String,
        age: String,
        name: String,
        email: String,
        password: String,
    ) {
        _userRegistrationStatus.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.createUser(sex, height, weight, age, name, email, password).data
            _userRegistrationStatus.value = Resource.Success(result)
        }
    }

}