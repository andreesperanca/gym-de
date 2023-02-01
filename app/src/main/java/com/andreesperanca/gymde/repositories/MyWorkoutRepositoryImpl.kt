package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

class MyWorkoutRepositoryImpl(
    private val firebaseDbService: FirebaseDbService

) : MyWorkoutRepository {

    override suspend fun fetchWorkouts(): Resource<List<Workout>> =
        firebaseDbService.fetchWorkouts()
}