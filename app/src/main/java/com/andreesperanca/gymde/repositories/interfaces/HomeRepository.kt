package com.andreesperanca.gymde.repositories.interfaces

import android.content.Context
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface HomeRepository {

    suspend fun fetchTodayWorkouts(context: Context) : Resource<List<Workout>>


}