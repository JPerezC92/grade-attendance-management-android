package com.example.gradeattendancemanagement.courserecord.components

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

    if (courseRecordContent is CourseRecordContent) {


        LazyColumn {
            this.items(count = 1, itemContent = {

                Text(text = courseRecordContent.courseRecord.career)
                Text(text = courseRecordContent.courseRecord.group)

                SelectActivity(courseRecordContent.activities, setActivity)

                SelectScore(
                    scores = scores.value,
                    setScoreId = setScoreId
                )

                if (scoreId?.value is Int) {
                    GradingScore(scoreId = scoreId, loading = getScoreAssignedLoading)
                }

            })
        }
    }

}


