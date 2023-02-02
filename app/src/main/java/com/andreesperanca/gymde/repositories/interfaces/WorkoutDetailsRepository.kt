package com.andreesperanca.gymde.repositories.interfaces

import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface WorkoutDetailsRepository {

    suspend fun excludeWorkout(workout: Workout) : Resource<Unit>

    suspend fun fetchExercises(workoutId: String): Resource<List<Exercise>>

}