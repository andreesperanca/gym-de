package com.andreesperanca.gymde.data

import androidx.appcompat.content.res.AppCompatResources
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.models.HealthArticles
import com.andreesperanca.gymde.models.Workouts
import java.sql.Timestamp


val mockWorkouts = listOf<Workouts>(
    Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        exercises = emptyList()
    ),
    Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        exercises = emptyList()
    ), Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        exercises = emptyList()
    ), Workouts(
        R.drawable.mockphoto1,
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
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