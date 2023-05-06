package com.example.task_manager_new.model
import java.io.Serializable

data class Task(
    var title: String? = null,
    var desc: String? = null
) : Serializable
