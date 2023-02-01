package com.andreesperanca.gymde.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.toastCreator(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.snackBarCreator(message: String) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).show()
}