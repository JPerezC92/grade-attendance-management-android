package com.example.gradeattendancemanagement.courserecord.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gradeattendancemanagement.courserecord.types.Activity
import com.example.gradeattendancemanagement.miscellaneous.components.SelectMenu

@Composable
fun SelectActivity(
    activities: List<Activity>,
    setActivity: (Activity) -> Unit
) {

    var expandedActivity by remember { mutableStateOf(false) }


    SelectMenu(
        modifier = Modifier,
        menuItems = activities.map { activity -> activity.name },
        menuExpandedState = expandedActivity,
        placeholder = "Actividades",
        updateMenuExpandStatus = { expandedActivity = true },
        onDismissMenuView = { expandedActivity = false },
        onMenuItemclick = { newIndex, selectedIndex ->
            selectedIndex.value = newIndex
            setActivity(activities.filterIndexed { index, _ -> index === selectedIndex.value }[0])
            expandedActivity = false
        }
    )

}