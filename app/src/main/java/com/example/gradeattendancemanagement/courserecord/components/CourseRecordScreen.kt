package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetCourseRecordRepository
import com.example.gradeattendancemanagement.courserecord.types.Activity
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.courserecord.types.Score
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading

@Composable
fun CourseRecordScreen(
    courseRecordId: String
) {

    val active = remember { mutableStateOf("GRADE") }

    val authContext = LocalAuth.current

    val fetchCourseRecord = useFetch(
        repository = LaravelGetCourseRecordRepository(
            token = authContext.token!!,
            courseRecordId = courseRecordId
        )
    )

    val courseRecordContent = fetchCourseRecord.data?.payload

    var activity = remember { mutableStateOf<Activity?>(null) }
    var scores = remember { mutableStateOf<List<Score>>(emptyList()) }
    var scoreId = remember { mutableStateOf<Int?>(null) }


    val getScoreAssignedLoading = useLoading()
    val setActivity = { newActivity: Activity -> activity.value = newActivity }

    val setScoreId = fun(newScoreId: Int) {
        getScoreAssignedLoading.startLoading()
        scoreId.value = newScoreId
    }

    LaunchedEffect(activity.value) {
        activity.value?.let {
            scores.value = activity.value!!.scores

            scoreId.value?.let { setScoreId(scores.value[0].id) }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row {

            Button(onClick = { active.value = "GRADE" }) {
                Text(text = "Calificaciones")
            }
            Button(onClick = { active.value = "ATTENDANCE" }) {
                Text(text = "Asistencias")
            }
        }

        if (courseRecordContent !== null) {

            Text(text = "Carrera: ${courseRecordContent.courseRecord.career}")
            Text(text = "Semestre: ${courseRecordContent.courseRecord.semester}")
            Text(text = "Grupo: ${courseRecordContent.courseRecord.group}")
            Text(text = "Turno: ${courseRecordContent.courseRecord.turn}")
        }

        if (courseRecordContent is CourseRecordContent) {

            if (active.value === "GRADE") {
                CourseRecordGrade(
                    courseRecordContent = courseRecordContent,
                    setActivity = setActivity,
                    scores = scores,
                    scoreId = scoreId,
                    setScoreId = setScoreId,
                    getScoreAssignedLoading = getScoreAssignedLoading
                )
            }

            if (active.value === "ATTENDANCE") {
                CourseRecordAttendance(courseRecordContent = courseRecordContent)
            }
        }
    }
}


