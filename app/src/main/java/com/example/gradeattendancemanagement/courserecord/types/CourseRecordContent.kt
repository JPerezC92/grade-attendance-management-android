package com.example.gradeattendancemanagement.courserecord.types

data class CourseRecordContent(
    val activities: List<Activity>,
    val attendances: List<Attendance>,
    val courseRecord: CourseRecord,
    val students: List<Student>
)