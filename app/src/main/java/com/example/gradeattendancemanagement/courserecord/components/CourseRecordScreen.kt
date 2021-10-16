package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    Column {

        Row {

            Button(onClick = { active.value = "GRADE" }) {
                Text(text = "Calificaciones")
            }
            Button(onClick = { active.value = "ATTENDANCE" }) {
                Text(text = "Asistencias")
            }
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


