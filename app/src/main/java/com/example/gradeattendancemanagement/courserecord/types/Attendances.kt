package com.example.gradeattendancemanagement.courserecord.types

data class Attendances(
    val attendancesCheck: List<AttendancesCheck>,
    val attended: Int,
    val attendedAverage: Int,
    val late: Int,
    val skip: Int
)