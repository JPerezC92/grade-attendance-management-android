package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gradeattendancemanagement.courserecord.types.Score
import com.example.gradeattendancemanagement.miscellaneous.components.SelectMenu

@Composable
fun SelectScore(scores: List<Score>, setScoreId: (Int) -> Unit) {

    var expandedScore by remember { mutableStateOf(false) }

    SelectMenu(
        modifier = Modifier,
        menuItems = scores.map { score -> score.name },
        menuExpandedState = expandedScore,
        placeholder = "Calificaciones",
        updateMenuExpandStatus = { expandedScore = true },
        onDismissMenuView = { expandedScore = false },
        onMenuItemclick = { newIndex, selectedIndex ->
            selectedIndex.value = newIndex
            expandedScore = false
            setScoreId(scores.filterIndexed { index, _ -> index === selectedIndex.value }[0].id)
        }
    )
}