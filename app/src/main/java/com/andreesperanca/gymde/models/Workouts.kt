package com.andreesperanca.gymde.models

import androidx.annotation.DrawableRes
import java.sql.Timestamp

data class Workouts(
    val name: String,
    val description: String,
    val date: Timestamp,

    val dayOfWeek: String,
    val exercises: List<Exercises>
)
