package com.example.gradeattendancemanagement.miscellaneous.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap


//fun interface UseForm<Values : HashMap<String, String>> {
//    fun invoke(values: Values): UseFormResult<Values>
//}

data class UseFormResult(
    val handleInputChange: (key: String, newValue: String) -> Unit,
    val values: SnapshotStateMap<String, String>
)


@Composable
fun useForm(vararg values: Pair<String, String>): UseFormResult {

    val values = remember { mutableStateMapOf(*values) }

    val handleInputChange =
        fun(key: String, newValue: String): Unit {
            values[key] = newValue
        }

    return UseFormResult(handleInputChange = handleInputChange, values = values)
}
