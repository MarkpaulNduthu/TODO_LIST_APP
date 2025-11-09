package com.mwema.todolist.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwema.todolist.data.TODORepository
import com.mwema.todolist.data.TODOTable
import com.mwema.todolist.util.Routes
import com.mwema.todolist.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TODOViewModel(
    private val repo: TODORepository
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var delTodo: TODOTable? = null
    val todos  = repo.getAllTodos()

    fun executeEvent(event: TODOListEvent) {
        when (event) {
            is TODOListEvent.DeleteTODO -> {
                viewModelScope.launch {
                    delTodo = event.todo
                    repo.removeTodo(event.todo)
                    sendUIEvent(
                        UiEvent.SnackBar(
                            message = "Todo Item Deleted",
                            action = "undo"
                        )
                    )
                }
            }

            TODOListEvent.OnAddTODOClick -> {
                sendUIEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is TODOListEvent.OnCheckChange -> {
                event.todo.isCompleted = event.isChecked
                viewModelScope.launch {
                    repo.addTodo(event.todo)
                }
            }

            is TODOListEvent.OnTODOClick -> {
                sendUIEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "todoId =${event.todo.id}"))
            }

            is TODOListEvent.OnUndoDelete -> {
                delTodo?.let {
                    viewModelScope.launch {
                        repo.addTodo(delTodo!!)
                    }
                }
            }
        }
    }

    fun sendUIEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}