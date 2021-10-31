package com.example.gradeattendancemanagement.courserecord.types

data class ActivityX(
    val average: Float?,
    val courseRecordId: Int,
    val created_at: String,
    val id: Int,
    val name: String,
    val scoresAssigned: List<ScoresAssigned>,
    val scoresQuantity: Int,
    val updated_at: String,
    val value: Int
)