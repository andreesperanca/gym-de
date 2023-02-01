package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface WorkoutDetailsRepository {

    suspend fun excludeWorkout(workout: Workout) : Resource<Unit>

}