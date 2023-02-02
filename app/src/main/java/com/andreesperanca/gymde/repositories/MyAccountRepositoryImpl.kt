package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.repositories.interfaces.MyAccountRepository
import com.andreesperanca.gymde.utils.Resource

class MyAccountRepositoryImpl(private val firebaseDbService: FirebaseDbService) :
    MyAccountRepository {

    override suspend fun fetchUser(): Resource<User> = firebaseDbService.fetchUser()
}