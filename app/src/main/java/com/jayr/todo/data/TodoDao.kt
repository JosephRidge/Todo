package com.jayr.todo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//Data access object here we define how we want to reterive the data
@Dao
interface TodoDao {
//    get | delete | insert todos
    @Insert(onConflict = OnConflictStrategy.REPLACE) // incase there esits similar item
    suspend fun insertTodo(todo: Todo) // asynchronous

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoNyId(id:Int):Todo? // returns nullable if itdoes not exisrs

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>> // get realtime updates when new items /updated list of todos

}