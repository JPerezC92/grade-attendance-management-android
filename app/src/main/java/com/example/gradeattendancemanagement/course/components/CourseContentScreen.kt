package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch

@Composable
fun CourseContentScreen(courseId: String) {

    val authContext = LocalAuth.current

    val fetchCourseContent =
        useFetch(
            repository = LaravelGetCourseContentRepository(
                token = authContext.token!!,
                courseId = courseId
            )
        )

    val courseContent = fetchCourseContent.data?.payload

    if (courseContent is CourseContent) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = courseContent.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
            )

            courseContent.course_records.map { courseRecord: CourseRecord ->
                CourseRecordCard(courseRecord)
            }

        }
    }
}
