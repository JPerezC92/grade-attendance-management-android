package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetCourseRecordRepository
import com.example.gradeattendancemanagement.courserecord.types.Activity
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.courserecord.types.Score
import com.example.gradeattendancemanagement.miscellaneous.components.SelectMenu
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

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


    val loading = useLoading()
    val setActivity = { newActivity: Activity -> activity.value = newActivity }

    val setScoreId = fun(newScoreId: Int) {
        loading.startLoading()
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
                    GradingScore(scoreId = scoreId, loading = loading)
                }

            })
        }
    }

}


@Composable
fun SelectActivity(
    activities: List<Activity>,
    setActivity: (Activity) -> Unit
) {

    var expandedActivity by remember { mutableStateOf(false) }


    SelectMenu(
        menuItems = activities.map { activity -> activity.name },
        menuExpandedState = expandedActivity,
//        seletedIndex = selectedIndexActivity,
        placeholder = "Actividades",
        updateMenuExpandStatus = { expandedActivity = true },
        onDismissMenuView = { expandedActivity = false },
        onMenuItemclick = { newIndex, selectedIndex ->

            selectedIndex.value = newIndex
            setActivity(activities.filterIndexed { index, _ -> index === selectedIndex.value }[0])
            expandedActivity = false

        }
    )

}

@Composable
fun SelectScore(scores: List<Score>, setScoreId: (Int) -> Unit) {

    var expandedScore by remember { mutableStateOf(false) }

    SelectMenu(
        menuItems = scores.map { score -> score.name },
        menuExpandedState = expandedScore,
//        seletedIndex = selectedIndexScore,
        placeholder = "Calificaciones",
        updateMenuExpandStatus = { expandedScore = true },
        onDismissMenuView = { expandedScore = false },
        onMenuItemclick = { newIndex, selectedIndex ->
            selectedIndex.value = newIndex
            expandedScore = false
            setScoreId(scores.filterIndexed { index, _ -> index === selectedIndex.value }[0].id)
        }
    )
}