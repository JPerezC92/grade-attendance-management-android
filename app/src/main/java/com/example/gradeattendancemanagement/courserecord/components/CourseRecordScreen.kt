package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetCourseRecordRepository
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.courserecord.types.Student
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch

@Composable
fun CourseRecordScreen(
    courseRecordId: String
) {

    val authContext = LocalAuth.current

    val fetchCourseRecord = useFetch(
        repository = LaravelGetCourseRecordRepository(
            token = authContext.token!!,
            courseRecordId = courseRecordId
        )
    )

    val courseRecordContent = fetchCourseRecord.data?.payload

    if (courseRecordContent is CourseRecordContent) {


        Column {
            Text(text = courseRecordContent.courseRecord.career)
            Text(text = courseRecordContent.courseRecord.group)
            courseRecordContent.students.map { student: Student -> Text(text = student.firstname) }
        }
    }

}