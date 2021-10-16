package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun GradingScore(scoreId: MutableState<Int?>, loading: UseLoadingResult) {

    val scoreAssignedContent = remember { mutableStateOf<List<ScoreAssignedContent>?>(null) }

    val putScoreAssignedLoading = useLoading()

    val setScoreAssignedContent = fun(newScoreAssignedContent: List<ScoreAssignedContent>) {
        scoreAssignedContent.value = newScoreAssignedContent
    }

    if (loading.isLoading === true) {
        SendGetScoreAssignedContentRequest(
            scoreId = scoreId.value.toString(),
            setScoreAssignedContent = setScoreAssignedContent,
            loading = loading
        )
        CircularProgressIndicator()
    } else {

        Column {
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

            Button(onClick = { putScoreAssignedLoading.startLoading() }) {
                Text(text = "Guardar")
            }

            if (putScoreAssignedLoading.isLoading) {
                CircularProgressIndicator()

                SendPutCScoreAssignedRequest(
                    scoreAssignedContent = scoreAssignedContent.value!!,
                    loading = putScoreAssignedLoading
                )
            }

        }
    }
}
