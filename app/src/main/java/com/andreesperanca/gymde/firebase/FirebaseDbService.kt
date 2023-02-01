package com.andreesperanca.gymde.firebase

import android.util.Log
import com.andreesperanca.gymde.data.mockWorkouts
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

class FirebaseDbService(
    private val firebaseDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
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

    suspend fun fetchWorkouts() : Resource<List<Workout>> {
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
}