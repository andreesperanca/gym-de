package com.andreesperanca.gymde.ui.login_and_register.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.repositories.LoginAndRegisterRepository
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.safeCall
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.rpc.context.AttributeContext.Auth
import com.google.rpc.context.AttributeContext.ResourceOrBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginAndRegisterViewModel(
    private val repository: LoginAndRegisterRepository
) : ViewModel() {

    private val _userRegistrationStatus = MutableLiveData<Resource<AuthResult>>()
    val userRegistrationStatus: LiveData<Resource<AuthResult>> = _userRegistrationStatus

    private val _loggedUser = MutableLiveData<Resource<FirebaseUser?>>()
    val loggedUser: LiveData<Resource<FirebaseUser?>> = _loggedUser

    private val _login = MutableLiveData<Resource<AuthResult>>()
    val login: LiveData<Resource<AuthResult>> = _login

    fun login(email: String, password: String) {
        _login.value = Resource.Loading()
        viewModelScope.launch {
            val authResult = repository.login(email, password)
            _login.value = authResult
        }
    }
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