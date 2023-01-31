package com.andreesperanca.gymde.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String = "",
    var sex: String = "",
    var height: String = "",
    var weight: String = "",
    var years: String = "",
    var email: String = ""
) : Parcelable
