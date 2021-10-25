package com.example.gradeattendancemanagement.miscellaneous.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableScreen() {
    // Just a fake data... a Pair of Int and String
    val tableData = (1..100).mapIndexed { index, item ->
        index to "Item $index"
    }
    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                Text(
                    text = "123",
                    Modifier
                        .fillParentMaxWidth(0.7F)
                        .border(1.dp, Color.Black)
                        .padding(8.dp),
                )
                Text(
                    text = "123",
                    Modifier
                        .fillParentMaxWidth(0.1F)
                        .border(1.dp, Color.Black)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "123",
                    Modifier
                        .fillParentMaxWidth(0.1F)
                        .border(1.dp, Color.Black)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "123",
                    Modifier
                        .fillParentMaxWidth(0.1F)
                        .border(1.dp, Color.Black)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
//                TableCell(text = "Column 1", weight = column1Weight)
//                TableCell(text = "Column 2", weight = column2Weight)
            }
        }
        // Here are all the lines of your table.
        items(1) {
            tableData.map {
                val (id, text) = it
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
//                    TableCell(text = id.toString(), weight = column1Weight)
//                    TableCell(text = text, weight = column2Weight)
                }
            }
        }
    }
}