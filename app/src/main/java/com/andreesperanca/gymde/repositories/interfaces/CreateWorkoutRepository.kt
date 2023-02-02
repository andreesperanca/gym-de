package com.andreesperanca.gymde.repositories.interfaces

import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface CreateWorkoutRepository {

    suspend fun createWorkout(newWorkout: Workout) : Resource<Unit>

}