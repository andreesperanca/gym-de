package com.andreesperanca.gymde.repositories.interfaces

import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.utils.Resource

interface MyAccountRepository {

    suspend fun fetchUser() : Resource<User>
}