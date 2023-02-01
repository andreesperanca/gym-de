package com.andreesperanca.gymde.firebase

import android.content.Context
import android.net.Uri
import android.util.Log
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.safeCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.absoluteValue

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
                    .get()
                    .await()

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

    suspend fun fetchExercises(workoutId: String): Resource<List<Exercise>> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val exercises = firebaseDb
                    .collection("exercises")
                    .whereEqualTo("workoutId", workoutId)
                    .get()
                    .await()

                val objectExercises = exercises.toObjects(Exercise::class.java)

                Resource.Success(objectExercises)
            }
        }
    }

    suspend fun fetchTodayWorkouts(context: Context): Resource<List<Workout>> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val today: String = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                    1 -> {context.getString(R.string.sunday)}
                    2 -> {context.getString(R.string.monday)}
                    3 -> {context.getString(R.string.tuesday)}
                    4 -> {context.getString(R.string.wednesday)}
                    5 -> {context.getString(R.string.thursday)}
                    6 -> {context.getString(R.string.friday)}
                    7 -> {context.getString(R.string.saturday)}
                    else -> {context.getString(R.string.sunday)}
                }

                Log.i("dayWeek",today.toString())

                val exercises = firebaseDb
                    .collection("users")
                    .document(firebaseAuth.uid!!)
                    .collection("workoutList")
                    .whereArrayContains("dayOfWeek", today)
                    .get()
                    .await()

                val objectWorkouts = exercises.toObjects(Workout::class.java)

                Resource.Success(objectWorkouts)
            }
        }
    }
}