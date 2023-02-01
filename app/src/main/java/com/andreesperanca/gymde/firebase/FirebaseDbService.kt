package com.andreesperanca.gymde.firebase

import android.net.Uri
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import com.andreesperanca.gymde.data.mockWorkouts
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.safeCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.net.URI

class FirebaseDbService(
    private val firebaseDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirebaseStorage: FirebaseStorage
) {

    suspend fun createWorkout(newWorkout: Workout): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val uid = newWorkout.uid
                val newWorkoutRef = firebaseDb
                    .collection("users")
                    .document(firebaseAuth.uid!!)
                    .collection("workoutList")
                    .document(uid.toString())
                    .set(newWorkout)
                Resource.Success(null)
            }
        }
    }

    suspend fun deleteWorkout(workout: Workout): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            safeCall {
                firebaseDb
                    .collection("users")
                    .document(firebaseAuth.uid!!)
                    .collection("workoutList")
                    .document(workout.uid.toString())
                    .delete()
                Resource.Success(Unit)
            }
        }
    }

    suspend fun fetchWorkouts(): Resource<List<Workout>> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val workouts = firebaseDb
                    .collection("users")
                    .document(firebaseAuth.uid!!)
                    .collection("workoutList")
                    .get().await()

                val objectWorkout = workouts.toObjects(Workout::class.java)

                Resource.Success(objectWorkout)
            }
        }
    }

    suspend fun updateWorkout(
        workout: Workout,
        newName: String,
        newDescription: String
    ): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            safeCall {
                firebaseDb
                    .collection("users")
                    .document(firebaseAuth.uid!!)
                    .collection("workoutList")
                    .document(workout.uid)
                    .update("name", newName).await()

                firebaseDb
                    .collection("users")
                    .document(firebaseAuth.uid!!)
                    .collection("workoutList")
                    .document(workout.uid)
                    .update("description", newDescription).await()

                Resource.Success(Unit)
            }
        }
    }

    suspend fun createExercise(newExercise: Exercise): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            safeCall {
                firebaseDb
                    .collection("exercises")
                    .document()
                    .set(newExercise)
                Resource.Success(Unit)
            }
        }
    }

    suspend fun uploadPhoto(Uri: Uri): Resource<Uri> {
        return withContext(Dispatchers.IO) {

            val uploadTask = firebaseFirebaseStorage
                .reference
                .child("exercisesImages/${Uri.lastPathSegment}")
                .putFile(Uri)
                .await()
            val linkDownload: Uri = uploadTask.storage.downloadUrl.await()

            Log.i("linkdownload", linkDownload.toString())

            Resource.Success(linkDownload)
        }
    }
}