package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

class WorkoutDetailsRepositoryImpl(
    private val firebaseDbService: FirebaseDbService
) : WorkoutDetailsRepository {

    override suspend fun excludeWorkout(workout: Workout): Resource<Unit> =
        firebaseDbService.deleteWorkout(workout)
}