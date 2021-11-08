package com.example.gradeattendancemanagement.miscellaneous.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Grading
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gradeattendancemanagement.R
import kotlinx.coroutines.CoroutineScope
import okio.Utf8.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.compose.rememberNavController
import com.example.gradeattendancemanagement.RouterApp
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.miscellaneous.context.useRouterContext
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter
import com.example.gradeattendancemanagement.miscellaneous.utils.Route
import kotlinx.coroutines.launch
import java.nio.file.Files.size


@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val router = LocalRouter.current
    Column {
        Image(
/*
                painter = painterResource(id = R.drawable.image_user),
                contentDescription = "logo Image",
                modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

        )

        Text(
                text = "User1",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic),
                modifier = Modifier
                    .padding(10.dp)
        )

        Text(
                text = "user1@gmail.com",
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .padding(10.dp)

 */
            painter = painterResource(id = R.drawable.image_user),
            contentDescription = "logo Image",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )

        Text(
            text = "USER_NAME"
        )

        Text(
            text = "USER-EMAIL"
        )

        Divider(
            modifier = Modifier
                .height(1.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )

        DrawerItem(selected = false)


        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
}


@Composable
fun DrawerItem(
    selected: Boolean
) {

    val authContext = LocalAuth.current
    val router = LocalRouter.current

    Column {
        if (router.navTitle == "Calificaciones" || router.navTitle == "Asistencias") {
            //CALIFICACIONES
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(12))
                    .background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
                    .padding(8.dp)
                    .clickable {
                        router.navToCourseRecordGrade()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Default.Grade,
                    contentDescription = "Calificaciones",
                    tint = if (selected) Color.Blue else Color.Gray
                )
                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
                Text(
                    text = "Calificaciones",
                    style = TextStyle(fontSize = 18.sp),
                    color = if (selected) Color.Blue else Color.Black
                )
            }
            //ASISTENCIA
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(12))
                    .background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
                    .padding(8.dp)
                    .clickable {
                        router.navToCourseRecordAttendance()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Default.Grading,
                    contentDescription = "Asistencias",
                    tint = if (selected) Color.Blue else Color.Gray
                )
                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
                Text(
                    text = "Asistencias",
                    style = TextStyle(fontSize = 18.sp),
                    color = if (selected) Color.Blue else Color.Black
                )
            }
        }

        //REGRESAR
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(56.dp)
//                .padding(6.dp)
//                .clip(RoundedCornerShape(12))
//                .background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
//                .padding(8.dp)
//                .clickable { },
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                modifier = Modifier
//                    .size(32.dp),
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = "Regresar",
//                tint = if (selected) Color.Blue else Color.Gray
//            )
//            Spacer(
//                modifier = Modifier
//                    .width(12.dp)
//            )
//            Text(
//                text = "Regresar",
//                style = TextStyle(fontSize = 18.sp),
//                color = if (selected) Color.Blue else Color.Black
//            )
//        }
        //CERRAR SESION
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(6.dp)
                .clip(RoundedCornerShape(12))
                .background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
                .padding(8.dp)
                .clickable { authContext.logout() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = Icons.Default.Logout,
                contentDescription = "Cerrar Sesion",
                tint = if (selected) Color.Blue else Color.Gray
            )
            Spacer(
                modifier = Modifier
                    .width(12.dp)
            )
            Text(
                text = "Cerrar Sesión",
                style = TextStyle(fontSize = 18.sp),
                color = if (selected) Color.Blue else Color.Black
            )
        }
    }
}