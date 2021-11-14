package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
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
import com.example.gradeattendancemanagement.miscellaneous.components.CustomCircularProgress
import com.example.gradeattendancemanagement.miscellaneous.components.Dialog
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun GradingScore(scoreId: MutableState<Int?>, loading: UseLoadingResult, courseRecordId: String) {

    val openDialog = remember { mutableStateOf(false) }
    val currentScoreAssignedContent = remember { mutableStateOf<ScoreAssignedContent?>(null) }

    val scoreAssignedContent = remember { mutableStateOf<List<ScoreAssignedContent>?>(null) }

    val putScoreAssignedByIdLoading = useLoading()

    val setScoreAssignedContent = fun(newScoreAssignedContent: List<ScoreAssignedContent>) {
        scoreAssignedContent.value = newScoreAssignedContent
    }

    if (loading.isLoading) {
        SendGetScoreAssignedContentRequest(
            scoreId = scoreId.value.toString(),
            courseRecordId = courseRecordId,
            setScoreAssignedContent = setScoreAssignedContent,
            loading = loading
        )

        CustomCircularProgress(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
    } else {


        Row(
            modifier = Modifier
                .fillMaxWidth(1.1F)
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Nombres",
                modifier = Modifier
                    .fillMaxWidth(0.6F)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )


            Text(
                text = "Notas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }

        Divider()

        scoreAssignedContent.value?.map { scoreAssigned ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(50.dp),
            ) {


                ClickableText(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .fillMaxHeight()
                        .padding(top = 15.dp)
                        .padding(horizontal = 10.dp),
                    text = buildAnnotatedString {
                        append("${scoreAssigned.lastname} ${scoreAssigned.firstname[0]}.")
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

            Divider()

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
                    text = "${currentScoreAssignedContent.value!!.lastname} ${currentScoreAssignedContent.value!!.firstname[0]}.",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(),
                    text = "${
                        currentScoreAssignedContent.value!!.updated_at.toDate()
                            .formatTo("dd MMM yyyy")
                    }",
                    style = TextStyle(
                        fontSize = 15.sp,
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
                        if (currentScoreAssignedContent.value!!.value.toString() == "0.0") {
                            "0"
                        } else {
                            scoreValue.value
                        }
                    },
                    onValueChange = { newValue ->

                        if (newValue > 20.toString()) {
                            ""
                        } else {
                            scoreValue.value = newValue

                            if (scoreValue.value.isNotEmpty()) {
                                currentScoreAssignedContent!!.value!!.value =
                                    newValue.trim().toFloatOrNull()
                            }
                        }
                    })
            }
        }
    )
}

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd HH:mm:ss",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

