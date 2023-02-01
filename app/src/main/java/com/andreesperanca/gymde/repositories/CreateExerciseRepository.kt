package com.andreesperanca.gymde.repositories

import android.net.Uri
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

interface CreateExerciseRepository {

    suspend fun createExercise(newExercise: Exercise) : Resource<Unit>

    suspend fun uploadPhoto(Uri: Uri): Resource<Uri>

}