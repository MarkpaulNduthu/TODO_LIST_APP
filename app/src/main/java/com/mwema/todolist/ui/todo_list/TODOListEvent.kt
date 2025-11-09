package com.mwema.todolist.ui.todo_list

import com.mwema.todolist.data.TODOTable

sealed class TODOListEvent {
    data class DeleteTODO(val todo: TODOTable) : TODOListEvent()
    object OnAddTODOClick : TODOListEvent()
    object OnUndoDelete : TODOListEvent()
    data class OnTODOClick(val todo: TODOTable) : TODOListEvent()
    data class OnCheckChange(
        val todo: TODOTable,
        val isChecked: Boolean
    ): TODOListEvent()
}