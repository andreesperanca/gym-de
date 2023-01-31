package com.andreesperanca.gymde.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.sql.Timestamp

@Parcelize
data class Workout(
    val name: String,
    val description: String,
    val date: Timestamp,

    val dayOfWeek: List<String>,
    val exercises:  @RawValue List<Exercise> = emptyList()
) : Parcelable
