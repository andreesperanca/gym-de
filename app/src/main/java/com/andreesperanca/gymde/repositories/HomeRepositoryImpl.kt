package com.andreesperanca.gymde.repositories

import android.content.Context
import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource
import com.google.firebase.storage.FirebaseStorage

class HomeRepositoryImpl(
    private val firebaseDbService: FirebaseDbService

) : HomeRepository {

    override suspend fun fetchTodayWorkouts(context: Context): Resource<List<Workout>> =
        firebaseDbService.fetchTodayWorkouts(context)
}