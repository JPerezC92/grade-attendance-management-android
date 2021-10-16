package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelPutAssignedAttendanceRepository
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AttendancesCheck
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun SendPutAssignedAttendanceRequest(
    attendancesCheck: List<AttendancesCheck>,
    loading: UseLoadingResult
) {

    val authContext = LocalAuth.current
    val fetchScoreAssigned = useFetch(
        repository = LaravelPutAssignedAttendanceRepository(
            token = authContext.token!!,
            attendancesCheck = attendancesCheck
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