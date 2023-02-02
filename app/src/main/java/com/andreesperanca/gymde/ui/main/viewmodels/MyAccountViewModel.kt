package com.andreesperanca.gymde.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.repositories.interfaces.MyAccountRepository
import com.andreesperanca.gymde.utils.Resource
import kotlinx.coroutines.launch

class MyAccountViewModel(private val repository: MyAccountRepository) : ViewModel() {


    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>> = _user

    fun fetchUser() {
        _user.value = Resource.Loading()
        viewModelScope.launch {
            val user = repository.fetchUser()
            _user.value = user
        }
    }

}