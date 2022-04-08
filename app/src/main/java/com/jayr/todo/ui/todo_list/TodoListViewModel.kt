package com.jayr.todo.ui.todo_list

import androidx.lifecycle.ViewModel
import com.jayr.todo.data.TodoRepository
import com.jayr.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel  @Inject constructor(
    private val repository: TodoRepository
):ViewModel(){
//    contain BLOC
//    State Variable
//    Repository log
    val todos = repository.getTodos()
//    channel used whe we wnt to share ontime events
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
}