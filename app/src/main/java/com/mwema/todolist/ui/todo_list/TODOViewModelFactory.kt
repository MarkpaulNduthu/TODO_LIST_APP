package com.mwema.todolist.ui.todo_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwema.todolist.data.TODORepository

class TODOViewModelFactory(
    private val repo: TODORepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TODOViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TODOViewModel(repo) as T
        }
        throw IllegalArgumentException("Wrong viewmodel class")
    }
}