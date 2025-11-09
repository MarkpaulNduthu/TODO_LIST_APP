package com.mwema.todolist.ui.addtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.mwema.todolist.data.TODORepositoryImplementation

class AddEditTodoViewModelFactory(
    val repo: TODORepositoryImplementation
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AddEditTODOViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddEditTODOViewModel(
                repo = repo,
                savedState = extras.createSavedStateHandle(),
            ) as T
        }
        throw IllegalArgumentException("Unknown model class!")
    }
}