package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.course.repositories.LaravelGetCoursesRepository
import com.example.gradeattendancemanagement.course.types.Course
import com.example.gradeattendancemanagement.miscellaneous.components.SplashScreen
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch


@Composable
fun CoursesScreen() {
    val authContext = LocalAuth.current

    val token = authContext.token ?: ""

    val fetchCourses =
        useFetch(repository = LaravelGetCoursesRepository(token = token))

    if (fetchCourses.data == null) {
        SplashScreen()

    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            this.items(count = 1, itemContent = {

                Text(
                    text = "Cursos",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                )
                fetchCourses.data?.payload?.map { course: Course -> CourseCard(course = course) }
            })
        }
    }
}

