package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetScoreAssignedContentRepository
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.hooks.useForm
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun GradingScore(scoreId: MutableState<Int?>, loading: UseLoadingResult) {

    val scoreAssignedContent = remember { mutableStateOf<List<ScoreAssignedContent>?>(null) }

    val setScoreAssignedContent = fun(newScoreAssignedContent: List<ScoreAssignedContent>) {
        scoreAssignedContent.value = newScoreAssignedContent
    }


    if (loading.isLoading === true) {
        SendGetCScoreAssignedContentRequest(
            scoreId = scoreId.value.toString(),
            setScoreAssignedContent = setScoreAssignedContent,
            loading = loading
        )
        CircularProgressIndicator()
    } else {


        Column() {

            scoreAssignedContent.value?.map { scoreAssigned ->
                Row {
                    Text(text = scoreAssigned.lastname)
                    TextField(
                        value = if (scoreAssigned.value === 0) "" else scoreAssigned.value.toString(),
                        onValueChange = { newValue ->
                            if (newValue.length !== 0)
                                scoreAssigned.value = Integer.parseInt(newValue.trim())
                            else
                                scoreAssigned.value = 0

                        })

                }
            }

        }
    }

}


@Composable
fun SendGetCScoreAssignedContentRequest(
    scoreId: String,
    setScoreAssignedContent: (List<ScoreAssignedContent>) -> Unit,
    loading: UseLoadingResult
) {
    val authContext = LocalAuth.current

    val fetchScoreAssignedContent = useFetch(
        repository = LaravelGetScoreAssignedContentRepository(
            scoreId = scoreId, token = authContext.token!!
        )
    )

    LaunchedEffect(fetchScoreAssignedContent.data) {

        if (fetchScoreAssignedContent.data?.success === true) {
            setScoreAssignedContent(fetchScoreAssignedContent.data.payload)
            loading.finishLoading()
        }

        if (fetchScoreAssignedContent.error is String) {
            loading.finishLoading()

        }
    }

}