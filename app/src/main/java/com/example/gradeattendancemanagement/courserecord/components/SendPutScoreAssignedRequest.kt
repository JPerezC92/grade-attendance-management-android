package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelPutScoreAssignedRepository
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun SendPutCScoreAssignedRequest(
    scoreAssignedContent: List<ScoreAssignedContent>,
    loading: UseLoadingResult
) {
    val authContext = LocalAuth.current
    val fetchScoreAssigned = useFetch(
        repository = LaravelPutScoreAssignedRepository(
            token = authContext.token!!, scoreAssignedContent = scoreAssignedContent
        )
    )

    LaunchedEffect(fetchScoreAssigned.data) {

        if (fetchScoreAssigned.data?.success === true) {
            loading.finishLoading()
        }

        if (fetchScoreAssigned.error is String) {
            loading.finishLoading()
        }
    }
}