package com.example.gradeattendancemanagement.course.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gradeattendancemanagement.course.types.CourseRecord
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun CourseRecordCard(courseRecord: CourseRecord) {

    val router = LocalRouter.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                router.setCurrentCourseRecord(courseRecord)
                router.navToCourseRecordGrade()
            },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {

            Text(text = "Carrera: " + courseRecord.career)
            Text(text = "Grupo: " + courseRecord.group)
            Text(text = "Periodo: " + courseRecord.period.value)
        }
    }
}