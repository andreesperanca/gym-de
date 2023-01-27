package com.andreesperanca.gymde.models

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import java.sql.Timestamp

data class Workouts(
    @DrawableRes val bg: Int,
    val name: String,
    val description: String,
    val data: Timestamp,
    val exercises: List<Exercises>
)
