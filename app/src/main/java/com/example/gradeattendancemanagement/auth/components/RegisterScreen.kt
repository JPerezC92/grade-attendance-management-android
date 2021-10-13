package com.example.gradeattendancemanagement.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun RegisterScreen() {

    val router = LocalRouter.current

    Column {
        Text(text = "RegisterScreen")

        Button(onClick = router.navToLogin) {
            Text(text = "Iniciar Sesion")
        }
    }
}