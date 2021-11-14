package com.example.gradeattendancemanagement.miscellaneous.types

import androidx.navigation.NavHostController
import com.example.gradeattendancemanagement.course.types.Course
import com.example.gradeattendancemanagement.course.types.CourseRecord

data class UseRouterContextResult(
    val routerAppController: NavHostController,
    val navToRegister: () -> Unit,
    val navToLogin: () -> Unit,
    val navToCourses: () -> Unit,
    val navToCourseContent: (courseId: String) -> Unit,
    val navToCourseRecordGrade: () -> Unit,
    val navToCourseRecordAttendance: () -> Unit,
    val navTitle: String,
    val setNavTitle: (String) -> Unit,
    val currentCourseRecord: CourseRecord?,
    val setCurrentCourseRecord: (CourseRecord) -> Unit,
    val currentCourse: Course?,
    val setCurrentCourse: (Course) -> Unit,
)
