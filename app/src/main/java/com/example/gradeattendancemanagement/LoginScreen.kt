package com.example.gradeattendancemanagement

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(navToRegister: () -> Unit) {
    Column {

        Text(text = "LoginScreen")

        Button(onClick = navToRegister) {

            Text(text = "Registrarse")
        }
    }
}