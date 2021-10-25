package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.components.RoundedButton
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun GradingScore(scoreId: MutableState<Int?>, loading: UseLoadingResult, courseRecordId: String) {

    val scoreAssignedContent = remember { mutableStateOf<List<ScoreAssignedContent>?>(null) }

    val putScoreAssignedLoading = useLoading()

    val setScoreAssignedContent = fun(newScoreAssignedContent: List<ScoreAssignedContent>) {
        scoreAssignedContent.value = newScoreAssignedContent
    }

    if (loading.isLoading === true) {
        SendGetScoreAssignedContentRequest(
            scoreId = scoreId.value.toString(),
            courseRecordId = courseRecordId,
            setScoreAssignedContent = setScoreAssignedContent,
            loading = loading
        )
        CircularProgressIndicator()
    } else {


        Row(
            modifier = Modifier
                .fillMaxWidth(1.1F)
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Nombres",
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .border(1.dp, Color.Black)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )


            Text(
                text = "Notas",
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }

        scoreAssignedContent.value?.map { scoreAssigned ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(50.dp),
            ) {

                Text(
                    text = "${scoreAssigned.firstname}",
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .border(1.dp, Color.Black)
                        .fillMaxHeight()
                        .padding(top = 15.dp)
                        .padding(horizontal = 10.dp),

                    textAlign = TextAlign.Justify
                )

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            RoundedButton(
                text = "Guardar",
                displayProgressBar = putScoreAssignedLoading.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                putScoreAssignedLoading.startLoading()
            }

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
