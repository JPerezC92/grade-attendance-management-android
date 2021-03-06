package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.course.repositories.LaravelGetCourseContentRepository
import com.example.gradeattendancemanagement.course.types.CourseContent
import com.example.gradeattendancemanagement.course.types.CourseRecord
import com.example.gradeattendancemanagement.miscellaneous.components.SplashScreen
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch

@Composable
fun CourseContentScreen(courseId: String) {

    val authContext = LocalAuth.current

    val token = authContext.token ?: ""

    val fetchCourseContent =
        useFetch(
            repository = LaravelGetCourseContentRepository(
                token = token,
                courseId = courseId
            )
        )

    val courseContent = fetchCourseContent.data?.payload

    if (courseContent is CourseContent) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            this.items(count = 1, itemContent = {
                Text(
                    text = courseContent.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                )

                courseContent.course_records.map { courseRecord: CourseRecord ->
                    CourseRecordCard(courseRecord)
                }
            })

        }
    } else {
        SplashScreen()
    }
}
