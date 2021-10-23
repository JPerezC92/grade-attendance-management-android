package com.example.gradeattendancemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gradeattendancemanagement.auth.components.nuevo.login.components.Login2
import com.example.gradeattendancemanagement.ui.theme.GradeAttendanceManagementTheme
import dev.leonardom.loginjetpackcompose.presentation.login.registration.RegistrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeAttendanceManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Store(context = this, content = { RouterApp() })
                    RegistrationScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GradeAttendanceManagementTheme {
        Greeting("Android")
    }
}