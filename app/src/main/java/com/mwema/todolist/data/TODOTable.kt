package com.mwema.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TODO_Table")
data class TODOTable(
    @PrimaryKey
    val id: Int?,
    val title: String,
    val description:String?,
    var isCompleted: Boolean
)
