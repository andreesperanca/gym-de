package com.andreesperanca.gymde.models

import android.os.Parcelable
import com.andreesperanca.gymde.utils.extensions.generateIdForFirebase
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp
import java.util.*

@Parcelize
data class Workout(
    val uid: String = UUID.randomUUID().generateIdForFirebase(),
    val name: String? = null,
    val description: String? = null,
    val date: Date? = null,
    val dayOfWeek: List<String>? = null
) : Parcelable
