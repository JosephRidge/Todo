package com.jayr.todo.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    //    get | delete | insert todos
     suspend fun insertTodo(todo: Todo) // asynchronous

     suspend fun deleteTodo(todo: Todo)

     suspend fun getTodoNyId(id:Int):Todo? // returns nullable if itdoes not exisrs

     fun getTodos(): Flow<List<Todo>> // get realtime updates when new items /updated list of todos

}