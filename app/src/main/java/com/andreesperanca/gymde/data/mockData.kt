package com.andreesperanca.gymde.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.models.HealthArticles
import com.andreesperanca.gymde.models.Workouts
import java.sql.Timestamp
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*

val mockWorkouts = listOf<Workouts>(
    Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Segunda",
        exercises = emptyList()
    ),
    Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Terça",
        exercises = emptyList()
    ), Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Quarta",
        exercises = emptyList()
    ), Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Quinta",
        exercises = emptyList()
    )
)

val healthArticles = listOf<HealthArticles>(
    HealthArticles(
        R.drawable.mockphoto2,
        title = "Alimentação",
        description = "AlimentacaAlimentacaoAlimaoAlimentacaoAlimentacaoAlimentacaoo"
    ),
    HealthArticles(
        R.drawable.mockphoto2,
        title = "Alimentação",
        description = "AlimentacaAlimentacaoAlimaoAlimentacaoAlimentacaoAlimentacaoo"
    )
)