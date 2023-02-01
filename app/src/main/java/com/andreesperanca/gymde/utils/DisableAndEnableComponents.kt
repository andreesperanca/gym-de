package com.andreesperanca.gymde.utils

import android.view.View


fun disableComponents(components: List<View>) {
    components.forEach { component ->
        component.isEnabled = false
    }
}

fun enableComponents(components: List<View>) {
    components.forEach { component ->
        component.isEnabled = true
    }
}
