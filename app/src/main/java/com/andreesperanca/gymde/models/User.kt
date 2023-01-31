package com.andreesperanca.gymde.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String = "",
    var sex: String = "",
    val height: String = "",
    val weight: String = "",
    val years: Int = 0,
    val email: String = ""
) : Parcelable
