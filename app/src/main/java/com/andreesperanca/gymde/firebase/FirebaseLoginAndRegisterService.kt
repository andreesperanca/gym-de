package com.andreesperanca.gymde.firebase

import com.andreesperanca.gymde.models.User
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.safeCall
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseLoginAndRegisterService(
    private val auth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore
) {

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    suspend fun createUser(
        sex: String,
        height: String,
        weight: String,
        age: String,
        name: String,
        email: String,
        password: String,
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val userId = result.user?.uid!!
                val newUser = User(userId, name, sex, height, weight, age, email)
                firebaseDb.collection("users").document(userId).set(newUser).await()
                Resource.Success(data = result)
            }

        }
    }

    suspend fun login(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val signInResult = auth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(signInResult)
            }
        }
    }

    fun logOut() = auth.signOut()

}