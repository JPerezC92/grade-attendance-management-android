package com.example.gradeattendancemanagement.courserecord.types

data class Activity(
    val courseRecordId: Int,
    val created_at: String,
    val id: Int,
    val name: String,
    val scores: List<Score>,
    val scoresQuantity: Int,
    val updated_at: String,
    val value: Int
)