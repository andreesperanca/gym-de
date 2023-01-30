package com.andreesperanca.gymde.data

import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.models.HealthArticles
import com.andreesperanca.gymde.models.Workouts
import java.sql.Timestamp

val mockWorkouts = listOf<Workouts>(
    Workouts(
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Segunda",
        exercises = emptyList()
    ),
    Workouts(
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Terça",
        exercises = emptyList()
    ), Workouts(
        "Treino de peito",
        "Treino para peito",
        Timestamp(System.currentTimeMillis()),
        "Quarta",
        exercises = emptyList()
    ), Workouts(
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
        title = "5 maneiras fáceis de incluir mais vegetais em sua dieta",
        description = "Comer mais vegetais é uma ótima maneira de melhorar sua saúde. Aqui estão algumas dicas simples para incluir mais vegetais em sua dieta diária.",
        content = ". Adicione vegetais ao seu café da manhã. Tente misturar alguns brócolis ou espinafre em seus ovos ou adicionar vegetais ralados ao seu smoothie.\n" +
                "\n" +
                "Faça lanches de vegetais. Corte vegetais como cenoura, pepino ou aipo em tiras finas e leve-os com você para o trabalho ou escola como lanche.\n" +
                "Faça uma salada como prato principal. Ao invés de usar a salada como acompanhamento, faça-a como prato principal e adicione proteínas como frango, atum ou feijão.\n" +
                "Adicione vegetais a suas massas. Adicione vegetais picados como brócolis, abobrinha ou tomate seco para sua massa para adicionar mais nutrientes.\n" +
                "Faça sopa. Sopa é uma ótima maneira de incluir mais vegetais em sua dieta. Experimente fazer sopa de legumes ou sopa de abóbora.\""
    ),
    HealthArticles(
        R.drawable.sono_photo,
        title = "Como o sono afeta sua saúde mental e física",
        description = "O sono é essencial para uma boa saúde mental e física. Descubra como o sono afeta seu corpo e mente e como melhorar sua qualidade de sono.",
        content = "O sono é essencial para uma boa saúde mental e física. Quando você dorme, seu corpo se recupera e se prepara para o dia seguinte. O sono também ajuda a regular seu humor, memória e capacidade de aprendizado.\n" +
                "\n" +
                "Falta de sono pode levar a problemas de saúde como obesidade, diabetes, pressão alta e doenças cardíacas. Também pode afetar sua saúde mental, causando problemas como ansiedade e depressão.\n" +
                "\n" +
                "Para melhorar sua qualidade de sono, tente seguir essas dicas:\n" +
                "\n" +
                "Estabeleça uma rotina de sono. Tente dormir e acordar ao mesmo tempo todos os dias, mesmo nos fins de semana.\n" +
                "Crie um ambiente propício para dormir. Mantenha seu quarto escuro, silencioso e fresco.\n" +
                "Evite estimulantes antes\n" +
                "\n" +
                "\n" +
                "\n"
    )
)