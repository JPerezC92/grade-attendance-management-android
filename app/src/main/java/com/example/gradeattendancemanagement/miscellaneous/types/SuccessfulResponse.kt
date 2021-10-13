package com.example.gradeattendancemanagement.miscellaneous.types

data class SuccessfulResponse<Payload>(
    var success: Boolean,
    var payload: Payload
)