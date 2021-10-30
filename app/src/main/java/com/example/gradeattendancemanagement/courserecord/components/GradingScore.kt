package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.components.Dialog
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun GradingScore(scoreId: MutableState<Int?>, loading: UseLoadingResult, courseRecordId: String) {

    val openDialog = remember { mutableStateOf(false) }
    val currentScoreAssignedContent = remember { mutableStateOf<ScoreAssignedContent?>(null) }

    val scoreAssignedContent = remember { mutableStateOf<List<ScoreAssignedContent>?>(null) }

    val putScoreAssignedByIdLoading = useLoading()

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


                ClickableText(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .border(1.dp, Color.Black)
                        .fillMaxHeight()
                        .padding(top = 15.dp)
                        .padding(horizontal = 10.dp),
                    text = buildAnnotatedString {
                        append("${scoreAssigned.firstname}")
                    },
                    onClick = {
                        currentScoreAssignedContent.value = scoreAssigned
                        openDialog.value = true
                    }

                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp),
                    onClick = {
                        currentScoreAssignedContent.value = scoreAssigned
                        openDialog.value = true
                    }) {
                    Text(text = if (scoreAssigned.value!!.toInt() === 0 || scoreAssigned.value === null) "Calificar" else scoreAssigned.value.toString())
                }


            }


        }

    }

    if (putScoreAssignedByIdLoading.isLoading && currentScoreAssignedContent.value !== null) {
        SendPutScoreAssignedByIdRequest(
            scoreAssigned = currentScoreAssignedContent.value!!,
            loading = putScoreAssignedByIdLoading
        )
    }

    Dialog(
        action = {
            putScoreAssignedByIdLoading.startLoading()
        },
        showDialog = openDialog.value,
        dismissDialog = { openDialog.value = false },
        content = {

            var scoreValue = remember {
                mutableStateOf(currentScoreAssignedContent.value!!.value.toString())
            }

            Column() {
                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(),
                    text = "${currentScoreAssignedContent.value!!.firstname}",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                TextField(
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ),

                    value = if (currentScoreAssignedContent.value!!.value === 0f || currentScoreAssignedContent.value!!.value == null) {
                        ""
                    } else {
                        scoreValue.value
                    },
                    onValueChange = { newValue ->

                        scoreValue.value = newValue

                        if (scoreValue.value.isNotEmpty()) {
                            currentScoreAssignedContent!!.value!!.value =
                                newValue.trim().toFloatOrNull()
                        }


                    })

            }
        }
    )
}

