package com.example.gradeattendancemanagement.courserecord.types

data class ScoreAssignedContent(
    val id: Int,
    val firstname: String,
    val lastname: String,
    var value: Int,
    val scoreId: Int,
    val studentId: Int,
    val activityId: Int,
    val created_at: String,
    val updated_at: String
)
