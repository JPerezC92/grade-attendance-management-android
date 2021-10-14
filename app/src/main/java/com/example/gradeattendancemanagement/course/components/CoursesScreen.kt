package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

    Column {

        authContext.user?.let { Text(text = it.firstname) }
        if (fetchCourses.isLoading) {
            CircularProgressIndicator()
        } else {
            fetchCourses.data?.payload?.map { course: Course -> CourseCard(course = course) }
        }


    }
}

