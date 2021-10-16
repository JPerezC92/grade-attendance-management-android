package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.gradeattendancemanagement.courserecord.types.Attendance
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AssignedAttendance
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AttendancesCheck
import com.example.gradeattendancemanagement.miscellaneous.components.SelectMenu
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading

@Composable
fun CourseRecordAttendance(courseRecordContent: CourseRecordContent) {

    var expanded = remember { mutableStateOf(false) }
    val attendance = remember { mutableStateOf<Attendance?>(null) }
    val assignedAttendance = remember {
        mutableStateOf(
            value = AssignedAttendance(
                emptyList(), emptyList()
            )
        )
    }

    val setAssignedAttendance = fun(newAssignedAttendance: AssignedAttendance) {
        assignedAttendance.value = newAssignedAttendance
    }

    val getAssignedAttendanceLoading = useLoading()
    val putAssignedAttendanceLoading = useLoading()

    LaunchedEffect(attendance.value) {
        if (attendance.value !== null) {
            getAssignedAttendanceLoading.startLoading()
        }
    }

    LazyColumn {
        this.items(count = 1, itemContent = {
            SelectMenu(
                menuItems = courseRecordContent.attendances.map { attendance: Attendance -> attendance.date },
                menuExpandedState = expanded.value,
                placeholder = "Seleccione una fecha",
                updateMenuExpandStatus = { expanded.value = true },
                onDismissMenuView = { expanded.value = false },
                onMenuItemclick = { newIndex, selectedIndex ->
                    selectedIndex.value = newIndex
                    attendance.value =
                        courseRecordContent.attendances.filterIndexed { index, _ -> index === selectedIndex.value }[0]
                    expanded.value = false
                }
            )

            if (getAssignedAttendanceLoading.isLoading) {
                SendGetAssignedAttendanceRequest(
                    attendanceId = attendance.value!!.id.toString(),
                    loading = getAssignedAttendanceLoading,
                    setAssignedAttendance = setAssignedAttendance
                )
            } else {

                if (assignedAttendance.value !== null
                    && assignedAttendance.value.attendancesCheck.isNotEmpty()
                ) {

                    assignedAttendance.value.attendancesCheck.map { attendancesCheck ->
                        Row {
                            Text(text = attendancesCheck.firstname)

                            assignedAttendance.value.attendanceStates.map { attendanceState ->
                                RadioButton(
                                    selected = if (attendancesCheck.attendanceStatusId !== null) attendanceState.id === attendancesCheck.attendanceStatusId else false,
                                    onClick = {
                                        attendancesCheck.attendanceStatusId = attendanceState.id
                                    })
                            }
                        }
                    }

                    Button(onClick = { putAssignedAttendanceLoading.startLoading() }) {
                        Text(text = "Guardar")
                    }

                    if (putAssignedAttendanceLoading.isLoading) {
                        CircularProgressIndicator()
                        SendPutAssignedAttendanceRequest(
                            attendancesCheck = assignedAttendance.value.attendancesCheck.filter { attendancesCheck -> attendancesCheck.attendanceStatusId !== null },
                            loading = putAssignedAttendanceLoading
                        )
                    }
                }
            }
        })
    }


}