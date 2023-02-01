package com.andreesperanca.gymde.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var uid : String? = null,
    var name: String? = null,
    var sex: String? = null,
    var height: String? = null,
    var weight: String? = null,
    var years: String? = null,
    var email: String? = null
) : Parcelable
