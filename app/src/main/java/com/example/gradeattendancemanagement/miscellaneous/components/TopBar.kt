package com.example.gradeattendancemanagement.miscellaneous.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.gradeattendancemanagement.R
import com.example.gradeattendancemanagement.miscellaneous.local.LocalContext
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
        scope : CoroutineScope,
        scaffoldState: ScaffoldState
) {
    val localRoute = LocalRouter.current
    TopAppBar(
            title = {  Text(localRoute.navTitle) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
                }
            }
    )
}