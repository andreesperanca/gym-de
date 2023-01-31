package com.andreesperanca.gymde.utils.extensions

import com.andreesperanca.gymde.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isValidPassword(): Boolean {
    this.error = null
    if (this.editText?.text?.length!! < 6) {
        this.error = this.context.getString(R.string.short_password)
        return false
    }
    return true
}

fun TextInputLayout.isValidEmail(): Boolean {
    val string = this.editText?.text
    this.error = null
    if (string != null) {
        if (string.isNotBlank() &&
            string.isNotEmpty() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches()
        ) {
            return true
        }
    }
    this.error = this.context.getString(R.string.no_valid_email)
    return false
}