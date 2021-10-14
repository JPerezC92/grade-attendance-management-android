package com.example.gradeattendancemanagement.courserecord.types

data class ScoresAssigned(
    val activityId: Int,
    val created_at: String,
    val id: Int,
    val scoreId: Int,
    val studentId: Int,
    val updated_at: String,
    val value: Int
)