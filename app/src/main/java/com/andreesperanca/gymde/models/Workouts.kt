package com.andreesperanca.gymde.models

import androidx.annotation.DrawableRes
import java.sql.Timestamp
import java.time.DayOfWeek

data class Workouts(
    @DrawableRes val bg: Int,
    val name: String,
    val description: String,
    val date: Timestamp,

    val dayOfWeek: String,
    val exercises: List<Exercises>
)
