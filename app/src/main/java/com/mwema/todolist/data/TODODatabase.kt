package com.mwema.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TODOTable::class], version = 1)
abstract class TODODatabase : RoomDatabase() {
    abstract fun todoDao(): TODODAO

    companion object {
        @Volatile
        var db: TODODatabase? = null
        fun getDB(context: Context): TODODatabase {
            return db ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context,
                    TODODatabase::class.java,
                    "todo_db"
                ).build()
                db = instance
                instance
            }
        }
    }
}