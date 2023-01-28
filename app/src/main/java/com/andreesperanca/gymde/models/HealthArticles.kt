package com.andreesperanca.gymde.models

import androidx.annotation.DrawableRes

data class HealthArticles(
    @DrawableRes val bg: Int,
    val title: String,
    val description: String,
    val content: String
)
