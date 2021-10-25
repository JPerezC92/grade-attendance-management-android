package com.example.gradeattendancemanagement.miscellaneous.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(
    text: String, weight: Float,
    modifier: Modifier
) {
    Text(
        text = text,

        modifier = modifier
            .border(1.dp, Color.Black)
            .padding(8.dp)
    )
}