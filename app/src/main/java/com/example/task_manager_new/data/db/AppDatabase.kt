package com.example.task_manager_new.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_manager_new.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
