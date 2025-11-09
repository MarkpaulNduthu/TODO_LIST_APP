package com.mwema.todolist.data

import kotlinx.coroutines.flow.Flow

interface TODORepository {
    suspend fun addTodo(todo: TODOTable)

    suspend fun findTodoById(id: Int): TODOTable?

    fun getAllTodos(): Flow<List<TODOTable>>

    suspend fun removeTodo(todo: TODOTable)
}