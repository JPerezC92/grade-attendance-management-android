package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelPutScoreAssignedByIdRepository
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun SendPutScoreAssignedByIdRequest(
    scoreAssigned: ScoreAssignedContent,
    loading: UseLoadingResult
) {
    val authContext = LocalAuth.current
    val fetchScoreAssigned = useFetch(
        repository = LaravelPutScoreAssignedByIdRepository(
            token = authContext.token!!, scoreAssigned = scoreAssigned
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