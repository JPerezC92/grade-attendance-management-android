package com.example.gradeattendancemanagement.courserecord.types.assignedattendance

data class AssignedAttendance(
    val attendanceStates: List<AttendanceState>,
    val attendancesCheck: List<AttendancesCheck>
)