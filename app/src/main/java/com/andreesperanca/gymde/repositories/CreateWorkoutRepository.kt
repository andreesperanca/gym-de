package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface CreateWorkoutRepository {

    suspend fun createWorkout(newWorkout: Workout) : Resource<Unit>

}