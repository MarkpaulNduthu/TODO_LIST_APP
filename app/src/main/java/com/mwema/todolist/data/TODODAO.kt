package com.mwema.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TODODAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todo: TODOTable)

    @Query("SELECT * FROM TODO_Table WHERE id=:id")
    suspend fun findTodoById(id: Int): TODOTable?

    @Query("SELECT * FROM TODO_Table")
    fun getAllTodos(): Flow<List<TODOTable>>

    @Delete
    suspend fun removeTodo(todo: TODOTable)
}