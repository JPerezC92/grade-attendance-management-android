package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.gradeattendancemanagement.courserecord.types.Activity
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.courserecord.types.Score
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun CourseRecordGrade(
    courseRecordContent: CourseRecordContent,
    setActivity: (Activity) -> Unit,
    scores: MutableState<List<Score>>,
    scoreId: MutableState<Int?>,
    setScoreId: (Int) -> Unit,
    getScoreAssignedLoading: UseLoadingResult
) {
    LazyColumn {
        this.items(count = 1, itemContent = {

            Text(text = courseRecordContent.courseRecord.career)
            Text(text = courseRecordContent.courseRecord.group)

            SelectActivity(courseRecordContent.activities, setActivity)

            SelectScore(
                scores = scores.value,
                setScoreId = setScoreId
            )

            if (scoreId?.value is Int) {
                GradingScore(scoreId = scoreId, loading = getScoreAssignedLoading)
            }

        })
    }
}