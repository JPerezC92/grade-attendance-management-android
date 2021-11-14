package com.example.gradeattendancemanagement

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradeattendancemanagement.auth.components.*
import com.example.gradeattendancemanagement.miscellaneous.utils.Route.*
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.course.components.CourseContentScreen
import com.example.gradeattendancemanagement.course.components.CoursesScreen
import com.example.gradeattendancemanagement.courserecord.components.CourseRecordScreen
import com.example.gradeattendancemanagement.miscellaneous.components.SplashScreen
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun RouterApp() {

    val router = LocalRouter.current
    val authContext = LocalAuth.current
    val getUserRequestLoading = useLoading()

    LaunchedEffect(authContext.token) {
        authContext.token?.let { getUserRequestLoading.startLoading() }
    }

    if (getUserRequestLoading.isLoading) {
        authContext.token?.let { SendGetUserRequest(token = it, setUser = authContext.setUser) }
    }



    NavHost(navController = router.routerAppController, startDestination = SplashScreen.route) {
        composable(SplashScreen.route) {
            SplashScreen(navTo = router.navToLogin)
        }

        composable(LoginScreen.route) {
            IfUserAuthenticatedGotoApp {
                LoginScreen()
            }
        }

        composable(RegisterScreen.route) {
            IfUserAuthenticatedGotoApp {
                RegisterScreen()
            }
        }

        composable(CoursesScreen.route) {
            router.setNavTitle("Cursos")
            IfUserIsAuthenticated {
                Column() {
                    MainScreen() {
                        CoursesScreen()
                    }
                }
            }
        }

        composable(CourseContentScreen.route) {
            var courseId = it.arguments?.getString("courseId")
            router.setNavTitle("Registros")

            IfUserIsAuthenticated {
                Column() {
                    MainScreen() {
                        CourseContentScreen(courseId = courseId!!)
                    }
                }
            }
        }

        composable(CourseRecordScreen.route) {
            var courseRecordId = it.arguments?.getString("courseRecordId")
            var componentView = it.arguments?.getString("componentView")

            if (componentView !== null) {
                val title = if (componentView == "GRADE") "Calificaciones" else "Asistencias"
                router.setNavTitle(title)
            }


            if (
                courseRecordId !== null &&
                componentView !== null
            ) {

                IfUserIsAuthenticated {
                    Column() {
                        MainScreen() {
                            CourseRecordScreen(
                                courseRecordId = courseRecordId,
                                componentView = componentView
                            )
                        }
                    }
                }
            }
        }
    }
}