package com.example.gradeattendancemanagement.miscellaneous.utils

import android.widget.Toast
import androidx.activity.ComponentActivity


class Validator constructor(private val localContext: ComponentActivity) {
    private val isValid = true

    fun registerForm(
        firstname: String,
        lastname: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (firstname.isEmpty()) {
            Toast.makeText(
                localContext,
                "El campo nombre es requerido",
                Toast.LENGTH_SHORT
            ).show()

            return !isValid
        }

        if (lastname.isEmpty()) {
            Toast.makeText(
                localContext,
                "El campo apellidos es requerido",
                Toast.LENGTH_SHORT
            ).show()
            return !isValid
        }
        if (email.isEmpty()) {
            Toast.makeText(
                localContext,
                "El campo correo es requerido",
                Toast.LENGTH_SHORT
            ).show()
            return !isValid
        }

        if (password.isEmpty()) {
            Toast.makeText(
                localContext,
                "El campo contraseña es requerido",
                Toast.LENGTH_SHORT
            ).show()
            return !isValid
        }

        if (password != confirmPassword) {
            Toast.makeText(
                localContext,
                "Las contraseñas no coinciden",
                Toast.LENGTH_SHORT
            ).show()
            return !isValid
        }

        return isValid
    }

}

