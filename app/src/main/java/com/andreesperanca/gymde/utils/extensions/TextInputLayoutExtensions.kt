package com.andreesperanca.gymde.utils.extensions

import com.andreesperanca.gymde.R
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

fun TextInputLayout.text() : String {
    return this.editText?.text.toString()
}

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

fun TextInputLayout.isValidHeight(): Boolean {
    val string = this.editText?.text
    this.error = null
    if (string != null) {
        return if (string.isNotBlank() && string.isNotEmpty() && string.length == 3) {
            true
        } else {
            this.error = this.context.getString(R.string.no_valid_height)
            false
        }
    }
    return false
}

fun TextInputLayout.isValidWeight(): Boolean {
    val weight = this.editText?.text
    this.error = null
    if (weight != null && weight.isNotEmpty() && weight.isNotBlank()) {
        if (weight.toString().toInt() in 21..299) {
            return true
        }
    }
    error = this.context.getString(R.string.no_valid_weight)
    return false
}

fun TextInputLayout.isValidAge(): Boolean {
    val age = this.editText?.text
    error = null
    if (age != null && age.isNotEmpty() && age.isNotBlank()) {
        if (age.toString().toInt() in 2..149) {
            return true
        }
    }
    this.error = this.context.getString(R.string.no_valid_age)
    return false
}

fun TextInputLayout.isValidName(): Boolean {
    val name = this.editText?.text
    error = null
    if (name != null && name.isNotEmpty() && name.isNotBlank()) {
        return true
    }
    this.error = this.context.getString(R.string.no_valid_name)
    return false
}

fun TextInputLayout.isValidConfirmPassword(password: String): Boolean {
    val confirmPassword = this.editText?.text
    this.error = null
    if (confirmPassword != null && confirmPassword.isNotEmpty() && confirmPassword.isNotBlank()) {
        if (password == confirmPassword.toString()) {
            return true
        }
    }

    this.error = this.context.getString(R.string.no_valid_confirm_password)
    return false

}