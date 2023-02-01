package com.andreesperanca.gymde.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var uid : String? = null,
    var name: String = "",
    var sex: String = "",
    var height: String = "",
    var weight: String = "",
    var years: String = "",
    var email: String = "",
    var workoutList: List<Workout> = emptyList()
) : Parcelable
