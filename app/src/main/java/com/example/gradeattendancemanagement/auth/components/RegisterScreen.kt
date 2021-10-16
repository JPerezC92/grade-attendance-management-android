package com.example.gradeattendancemanagement.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.example.gradeattendancemanagement.auth.types.Register
import com.example.gradeattendancemanagement.miscellaneous.hooks.useForm
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter


enum class RegisterFields(val value: String) {

    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    EMAIL("email"),
    PASSWORD("password"),
}

@Composable
fun RegisterScreen() {

    val router = LocalRouter.current

    val form =
        useForm(
            RegisterFields.FIRSTNAME.value to "",
            RegisterFields.LASTNAME.value to "",
            RegisterFields.EMAIL.value to "",
            RegisterFields.PASSWORD.value to ""
        )

    val registerRequestLoading = useLoading()

    Column {

        if (registerRequestLoading.isLoading) {
            SendRegisterRequest(
                register = Register(
                    firstname = form.values[RegisterFields.FIRSTNAME.value]!!,
                    lastname = form.values[RegisterFields.LASTNAME.value]!!,
                    email = form.values[RegisterFields.EMAIL.value]!!,
                    password = form.values[RegisterFields.PASSWORD.value]!!
                ), loading = registerRequestLoading
            )
        }



        Text(text = "RegisterScreen")


        TextField(
            value = form.values[RegisterFields.FIRSTNAME.value]!!,
            onValueChange = { newValue ->
                form.handleInputChange(
                    RegisterFields.FIRSTNAME.value,
                    newValue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Nombres") }
        )
        TextField(
            value = form.values[RegisterFields.LASTNAME.value]!!,
            onValueChange = { newValue ->
                form.handleInputChange(
                    RegisterFields.LASTNAME.value,
                    newValue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Apellidos") }
        )
        TextField(
            value = form.values[RegisterFields.EMAIL.value]!!,
            onValueChange = { newValue ->
                form.handleInputChange(
                    RegisterFields.EMAIL.value,
                    newValue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Correo") }
        )

        TextField(
            value = form.values[RegisterFields.PASSWORD.value]!!,
            onValueChange = { newValue ->
                form.handleInputChange(
                    RegisterFields.PASSWORD.value,
                    newValue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Contraseña") }
        )


        Button(onClick = registerRequestLoading.startLoading) {
            Text(text = "Registrarse")

        }

        if (registerRequestLoading.isLoading) {
            CircularProgressIndicator()
        }

        Button(onClick = router.navToLogin) {
            Text(text = "Iniciar Sesion")
        }
    }
}