package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetCourseRecordRepository
import com.example.gradeattendancemanagement.courserecord.types.Activity
import com.example.gradeattendancemanagement.courserecord.types.Score
import com.example.gradeattendancemanagement.miscellaneous.components.SplashScreen
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading

@Composable
fun CourseRecordScreen(
    courseRecordId: String,
    componentView: String
) {
    val authContext = LocalAuth.current

    val token = authContext.token ?: ""

    val fetchCourseRecord = useFetch(
        repository = LaravelGetCourseRecordRepository(
            token = token,
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

    if (courseRecordContent == null) {
        SplashScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Text(text = "Carrera: ${courseRecordContent.courseRecord.career}")
            Text(text = "Semestre: ${courseRecordContent.courseRecord.semester}")
            Text(text = "Grupo: ${courseRecordContent.courseRecord.group}")
            Text(text = "Turno: ${courseRecordContent.courseRecord.turn}")

            if (componentView == "GRADE") {
                CourseRecordGrade(
                    courseRecordContent = courseRecordContent,
                    setActivity = setActivity,
                    scores = scores,
                    scoreId = scoreId,
                    setScoreId = setScoreId,
                    getScoreAssignedLoading = getScoreAssignedLoading
                )
            }

            if (componentView == "ATTENDANCE") {
                CourseRecordAttendance(courseRecordContent = courseRecordContent)
            }
        }
    }
}



