package com.andreesperanca.gymde.utils.extensions

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.isVisible(b: Boolean) {
    if (b) this.visibility = View.VISIBLE
    else this.visibility = View.INVISIBLE
}