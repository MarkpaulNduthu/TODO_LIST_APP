package com.mwema.todolist.ui.addtodo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwema.todolist.data.TODORepositoryImplementation
import com.mwema.todolist.data.TODOTable
import com.mwema.todolist.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditTODOViewModel(
    private val repo: TODORepositoryImplementation,
    private val savedState: SavedStateHandle
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    var todo: TODOTable? = null
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    init {
        val todoId = savedState.get<Int>("todoId")!!
        if (todoId != -1) {
            viewModelScope.launch {
                repo.findTodoById(todoId)?.let {
                    title = it.title
                    description = it.description ?: ""
                    this@AddEditTODOViewModel.todo = it
                }
            }
        }
    }

    fun executeEvent(event: AddEditTODOEvents) {
        when (event) {
            is AddEditTODOEvents.AddTODO -> {
                viewModelScope.launch {
                    repo.addTodo(
                        TODOTable(
                            id = todo?.id,
                            title = title,
                            description = description,
                            isCompleted = todo?.isCompleted ?: false
                        )
                    )
                    _uiEvent.send(UiEvent.PopBackStack)
                }
            }

            is AddEditTODOEvents.OnDescriptionChange -> {
                description = event.description
            }

            is AddEditTODOEvents.OnTitleChange -> {
                title = event.title
            }
        }
    }
}