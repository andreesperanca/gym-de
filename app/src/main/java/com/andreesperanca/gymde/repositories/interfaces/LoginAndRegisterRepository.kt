package com.andreesperanca.gymde.repositories.interfaces

import com.andreesperanca.gymde.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface LoginAndRegisterRepository {

    suspend fun createUser(
        sex: String,
        height: String,
        weight: String,
        age: String,
        name: String,
        email: String,
        password: String,
    ): Resource<AuthResult>

    fun getUser(): FirebaseUser?

    suspend fun login(email: String, password: String) : Resource<AuthResult>

}