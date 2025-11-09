package com.mwema.todolist.ui.todo_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mwema.todolist.data.TODOTable

@Composable
fun TODOItem(
    todo: TODOTable,
    modifier: Modifier = Modifier,
    onEvent: (TODOListEvent) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = {
                        onEvent(TODOListEvent.DeleteTODO(todo))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Todo Item"
                    )
                }
            }
            todo.description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = { isChecked ->
                onEvent(TODOListEvent.OnCheckChange(todo, isChecked))
            }
        )
    }
}