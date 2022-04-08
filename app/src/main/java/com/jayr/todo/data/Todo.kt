package com.jayr.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//contains all fiels within a todoription
@Entity
data class Todo(
    val title:String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id:Int? = null
)
