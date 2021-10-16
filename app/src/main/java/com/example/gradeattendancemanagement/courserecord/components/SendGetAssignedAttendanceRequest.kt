package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.courserecord.repositories.LaravelGetAssignedAttendanceRepository
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AssignedAttendance
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult


@Composable
fun SendGetAssignedAttendanceRequest(
    attendanceId: String,
    loading: UseLoadingResult,
    setAssignedAttendance: (AssignedAttendance) -> Unit
) {
    val authContext = LocalAuth.current
    val fetchAssignedAttendance =
        useFetch(
            repository = LaravelGetAssignedAttendanceRepository(
                authContext.token!!,
                attendanceId
            )
        )

    LaunchedEffect(fetchAssignedAttendance.data) {

        if (fetchAssignedAttendance.data?.success === true) {
            setAssignedAttendance(fetchAssignedAttendance.data.payload)
            loading.finishLoading()
        }

        if (fetchAssignedAttendance.error is String) {
            loading.finishLoading()

        }
    }
}