package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

        Column {

            Text(text = courseContent.name)

            courseContent.course_records.map { courseRecord: CourseRecord ->
                CourseRecordCard(courseRecord)
            }

        }
    }
}
