package com.mwema.todolist.data

import kotlinx.coroutines.flow.Flow

class TODORepositoryImplementation(
    private val dao: TODODAO
): TODORepository {
    override suspend fun addTodo(todo: TODOTable) {
        dao.addTodo(todo)
    }

    override suspend fun findTodoById(id: Int): TODOTable? {
        return dao.findTodoById(id)
    }

    override fun getAllTodos(): Flow<List<TODOTable>> {
        return dao.getAllTodos()
    }

    override suspend fun removeTodo(todo: TODOTable) {
        dao.removeTodo(todo)
    }
}