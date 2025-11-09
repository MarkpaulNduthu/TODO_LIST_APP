package com.mwema.todolist.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mwema.todolist.util.UiEvent
import kotlinx.coroutines.launch

@Composable
fun TODOScreen(
    viewModel: TODOViewModel,
    onNavigate: (UiEvent) -> Unit
) {
    var todos = viewModel.todos.collectAsState(
        initial = emptyList()
    )
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                is UiEvent.SnackBar -> {
                    scope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action
                        )
                        when (snackbarResult) {
                            SnackbarResult.ActionPerformed -> {
                                viewModel.executeEvent(TODOListEvent.OnUndoDelete)
                            }

                            else -> Unit
                        }

                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.executeEvent(TODOListEvent.OnAddTODOClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add todo item",
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = todos.value
                ) { item ->
                    TODOItem(
                        todo = item,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth()
                            .clickable {
                                viewModel.executeEvent(
                                    TODOListEvent.OnTODOClick(
                                        todo = item
                                    )
                                )
                            }
                    ) { todoListEvent ->
                        viewModel.executeEvent(todoListEvent)
                    }
                }
            }
        }
    }
}