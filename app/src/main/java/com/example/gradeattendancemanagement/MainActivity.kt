package com.example.gradeattendancemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.gradeattendancemanagement.miscellaneous.components.Drawer
import com.example.gradeattendancemanagement.miscellaneous.components.TopBar

import com.example.gradeattendancemanagement.ui.theme.GradeAttendanceManagementTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeAttendanceManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Store(context = this, content = { RouterApp() })

                }
            }
        }
    }
}

@Composable
fun MainScreen(content: @Composable () -> Unit ){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()

    Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(scope , scaffoldState )},
            drawerContent = { Drawer(scope, scaffoldState) },
            content = {
                content()
            }
    )
}