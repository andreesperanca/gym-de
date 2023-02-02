package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.firebase.FirebaseLoginAndRegisterService
import com.andreesperanca.gymde.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class LoginAndRegisterRepositoryImpl(
    private val firebaseService: FirebaseLoginAndRegisterService
) : LoginAndRegisterRepository {
    override fun getUser(): FirebaseUser? = firebaseService.getCurrentUser()
    override suspend fun createUser(
        sex: String,
        height: String,
        weight: String,
        age: String,
        name: String,
        email: String,
        password: String,
    ) = firebaseService.createUser(sex, height, weight, age, name, email, password)

    override suspend fun login(email: String, password: String): Resource<AuthResult> =
        firebaseService.login(email, password)

}