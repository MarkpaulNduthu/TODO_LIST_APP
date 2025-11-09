package com.mwema.todolist.ui.addtodo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mwema.todolist.util.UiEvent

@Composable
fun AddEditTODOScreen(
    viewModel: AddEditTODOViewModel,
    onPopbackStack: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopbackStack()
                else -> Unit
            }
        }
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.executeEvent(AddEditTODOEvents.AddTODO)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Edit/Add todo Item"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                value = viewModel.title,
                onValueChange = { value ->
                    viewModel.executeEvent(
                        AddEditTODOEvents.OnTitleChange(value)
                    )
                },
                placeholder = {
                    Text(
                        text = "Title"
                    )
                }
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                value = viewModel.description,
                onValueChange = { value ->
                    viewModel.executeEvent(
                        AddEditTODOEvents.OnDescriptionChange(value)
                    )
                },
                placeholder = {
                    Text(
                        text = "Description(Optional)"
                    )
                }
            )
        }
    }

}