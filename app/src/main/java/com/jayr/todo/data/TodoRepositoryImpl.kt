package com.jayr.todo.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao:TodoDao // dependency
): TodoRepository {
    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoNyId(id: Int): Todo? {
       return  dao.getTodoNyId(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

}