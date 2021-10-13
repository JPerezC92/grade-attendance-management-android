package com.example.gradeattendancemanagement.remote

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)