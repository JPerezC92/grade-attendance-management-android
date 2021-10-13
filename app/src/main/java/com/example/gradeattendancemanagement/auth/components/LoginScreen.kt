package com.example.gradeattendancemanagement.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.example.gradeattendancemanagement.auth.types.Credentials
import com.example.gradeattendancemanagement.miscellaneous.hooks.useForm
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

enum class LoginFields(val value: String) {
    EMAIL("email"),
    PASSWORD("password"),
}

@Composable
fun LoginScreen() {
    val router = LocalRouter.current

    val form =
        useForm(
            LoginFields.EMAIL.value to "test2@gmail.com",
            LoginFields.PASSWORD.value to "123456aA"
        )

    val loginRequestLoading = useLoading()

    Column {
        if (loginRequestLoading.isLoading === true) {
            SendLoginRequest(
                loading = loginRequestLoading,
                credentials = Credentials(
                    email = form.values[LoginFields.EMAIL.value]!!,
                    password = form.values[LoginFields.PASSWORD.value]!!
                )
            )
        }

        Text(text = "Iniciar sesion")

        TextField(
            value = form.values[LoginFields.EMAIL.value]!!,
            onValueChange = { newValue ->
                form.handleInputChange(
                    LoginFields.EMAIL.value,
                    newValue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Correo") }
        )

        Text(text = form.values[LoginFields.EMAIL.value]!!)

        TextField(
            value = form.values[LoginFields.PASSWORD.value]!!,
            onValueChange = { newValue ->
                form.handleInputChange(
                    LoginFields.PASSWORD.value,
                    newValue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = "Contraseña") }
        )

        if (!loginRequestLoading.isLoading) {
            Button(onClick = { loginRequestLoading.startLoading() }) {
                Text(text = "Iniciar sesion")
            }
        } else {
            CircularProgressIndicator()
        }

        Button(onClick = router.navToRegister) {
            Text(text = "Registrarse")
        }

    }
}

