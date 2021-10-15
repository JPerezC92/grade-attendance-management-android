package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetScoreAssignedRepository
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult


@Composable
fun SendGetScoreAssignedContentRequest(
    scoreId: String,
    setScoreAssignedContent: (List<ScoreAssignedContent>) -> Unit,
    loading: UseLoadingResult
) {
    val authContext = LocalAuth.current

    val fetchScoreAssignedContent = useFetch(
        repository = LaravelGetScoreAssignedRepository(
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