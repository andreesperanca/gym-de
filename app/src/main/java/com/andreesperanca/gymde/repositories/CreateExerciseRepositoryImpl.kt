package com.andreesperanca.gymde.repositories

import android.net.Uri
import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource

class CreateExerciseRepositoryImpl(
    private val firebaseDbService: FirebaseDbService
) : CreateExerciseRepository {

    override suspend fun createExercise(newExercise: Exercise): Resource<Unit> =
        firebaseDbService.createExercise(newExercise)

    override suspend fun uploadPhoto(Uri: Uri): Resource<Uri> =
        firebaseDbService.uploadPhoto(Uri)

}