package com.andreesperanca.gymde.repositories

import android.content.Context
import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.interfaces.HomeRepository
import com.andreesperanca.gymde.utils.Resource

class HomeRepositoryImpl(
    private val firebaseDbService: FirebaseDbService

) : HomeRepository {

    override suspend fun fetchTodayWorkouts(context: Context): Resource<List<Workout>> =
        firebaseDbService.fetchTodayWorkouts(context)
}