package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.course.repositories.LaravelGetCoursesRepository
import com.example.gradeattendancemanagement.course.types.Course
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading


@Composable
fun CoursesScreen() {
    val authContext = LocalAuth.current

    val fetchCourses =
        useFetch(repository = LaravelGetCoursesRepository(token = authContext.token!!))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

//        authContext.user?.let { Text(text = it.firstname) }

        if (fetchCourses.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text(
                text = "Cursos",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
            )
            fetchCourses.data?.payload?.map { course: Course -> CourseCard(course = course) }
        }


    }
}

