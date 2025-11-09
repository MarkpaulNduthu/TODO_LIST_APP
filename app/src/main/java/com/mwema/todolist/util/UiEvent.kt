package com.mwema.todolist.util

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class SnackBar(
        val message: String,
        val action: String? = null
    ) : UiEvent()
}