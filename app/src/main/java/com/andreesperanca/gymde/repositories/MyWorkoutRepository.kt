package com.andreesperanca.gymde.repositories

import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface MyWorkoutRepository {


    suspend fun fetchWorkouts() : Resource<List<Workout>>

}