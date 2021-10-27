package com.example.gradeattendancemanagement.miscellaneous.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dialog(
    showDialog: Boolean,
    dismissDialog: () -> Unit,
    content: @Composable () -> Unit,
    action: () -> Unit
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = {
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {

                content()

            },
            confirmButton = {

                Button(onClick = {
                    action()
                    dismissDialog()
                }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {

                Button(onClick = { dismissDialog() }) {
                    Text(text = "Cancelar")
                }
            }

        )


    }

}