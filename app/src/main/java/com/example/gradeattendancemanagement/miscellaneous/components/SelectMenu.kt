package com.example.gradeattendancemanagement.miscellaneous.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SelectMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    modifier: Modifier,
    placeholder: String,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemclick: (Int, MutableState<Int?>) -> Unit,
) {

    val selectedIndex = remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = modifier
            .padding(top = 10.dp)
            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
            .clickable(
                onClick = {
                    updateMenuExpandStatus()
                },
            ),

        ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row {


                Text(
                    text = if (selectedIndex.value !== null && selectedIndex.value !== null) menuItems[selectedIndex.value!!] else placeholder,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp, 20.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                if (menuItems !== null) {

                    menuItems.forEachIndexed { index, title ->
                        DropdownMenuItem(
                            onClick = {
//                            if (index != 0) {
                                onMenuItemclick(index, selectedIndex)
//                            }
                            }) {
                            Text(text = title)
                        }
                    }
                }
            }
        }
    }
}