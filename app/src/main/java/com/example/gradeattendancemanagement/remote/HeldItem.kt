package com.example.gradeattendancemanagement.remote

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)