package com.mwema.todolist.ui.addtodo

import com.mwema.todolist.data.TODOTable

sealed class AddEditTODOEvents {
    object AddTODO: AddEditTODOEvents()
    data class OnTitleChange(val title: String): AddEditTODOEvents()
    data class OnDescriptionChange(val description: String): AddEditTODOEvents()
}