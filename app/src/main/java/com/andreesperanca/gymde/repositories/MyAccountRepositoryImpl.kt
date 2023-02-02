package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.firebase.FirebaseLoginAndRegisterService
import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.utils.Resource

class MyAccountRepositoryImpl(
    private val firebaseDbService: FirebaseDbService,
    private val firebaseRegisterAndLoginService: FirebaseLoginAndRegisterService
    ) : MyAccountRepository {

    override suspend fun fetchUser(): Resource<User> = firebaseDbService.fetchUser()

    override fun logOut() = firebaseRegisterAndLoginService.logOut()
}