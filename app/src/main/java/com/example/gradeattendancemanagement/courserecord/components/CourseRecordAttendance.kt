package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gradeattendancemanagement.courserecord.types.Attendance
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AssignedAttendance
import com.example.gradeattendancemanagement.miscellaneous.components.RoundedButton
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
            Row(horizontalArrangement = Arrangement.Center) {

                SelectMenu(
                    modifier = Modifier.fillMaxWidth(0.5f),
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

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .fillMaxSize()
                        .padding(10.dp, top = 15.dp),

                    onClick = {
                        assignedAttendance.value.attendancesCheck.map { attendancesCheck ->
                            attendancesCheck.attendanceStatusId = 1
                        }
                    }
                ) {
                    Text(text = "Marcar todos")
                }
            }

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
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp),
                    ) {
//                    HEADERS
                        Text(
                            text = "Nombres",
                            Modifier
                                .fillParentMaxWidth(0.7F)
                                .padding(8.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "A",
                            Modifier
                                .fillParentMaxWidth(0.1F)
                                .padding(8.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "T",
                            Modifier
                                .fillParentMaxWidth(0.1F)
                                .padding(8.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "I",
                            Modifier
                                .fillParentMaxWidth(0.1F)
                                .padding(8.dp),
                            textAlign = TextAlign.Center
                        )

                    }

                    Divider()

                    assignedAttendance.value.attendancesCheck.map { attendancesCheck ->
                        Row() {
                            Text(
                                text = "${attendancesCheck.firstname}",
                                Modifier
                                    .fillParentMaxWidth(0.7F)
                                    .padding(8.dp)
                                    .height(25.dp)
                            )


                            assignedAttendance.value.attendanceStates.map { attendanceState ->
                                RadioButton(
                                    modifier = Modifier
                                        .fillParentMaxWidth(0.1F)
                                        .padding(8.dp)
                                        .height(25.dp),
                                    selected = if (attendancesCheck.attendanceStatusId !== null) attendanceState.id === attendancesCheck.attendanceStatusId else false,
                                    onClick = {
                                        attendancesCheck.attendanceStatusId = attendanceState.id
                                    })
                            }
                        }

                        Divider()
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
                            displayProgressBar = putAssignedAttendanceLoading.isLoading,
                            modifier = Modifier
                                .fillParentMaxWidth()
                        ) {
                            putAssignedAttendanceLoading.startLoading()
                        }

                    }


                    if (putAssignedAttendanceLoading.isLoading) {

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