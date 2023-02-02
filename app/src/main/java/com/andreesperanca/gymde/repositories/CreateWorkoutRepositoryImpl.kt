package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.interfaces.CreateWorkoutRepository
import com.andreesperanca.gymde.utils.Resource

class CreateWorkoutRepositoryImpl(
    private val firebaseDbService: FirebaseDbService

) : CreateWorkoutRepository {

    override suspend fun createWorkout(newWorkout: Workout) : Resource<Unit> =
        firebaseDbService.createWorkout(newWorkout)

}