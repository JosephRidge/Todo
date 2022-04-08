package com.jayr.todo.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.todo.data.TodoRepository
import com.jayr.todo.util.Routes
import com.jayr.todo.data.Todo
import com.jayr.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//inject repository
@HiltViewModel
class TodoListViewModel  @Inject constructor(
    private val repository: TodoRepository // daggerhilt wil go to the modules and look for the todorepository repository
    // it will generate code that will pass it behinfd the scenes
):ViewModel(){
//    contain Business Logic
//    State Variable
//    Repository log
//    our state
    val todos = repository.getTodos()
//    channel used whe we wnt to share one-time events{ thise that we dont want to assign a state } - here we use channels
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow() // expose it as an immutable event

    private var deletedTodo: Todo? = null
//    Function triggered on UI given the event, basically what to do when a specific event occurs
    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.OnAddTodoClick->{
//                _uiEvent.send(UiEvent.Navigate(Routes.ADD_EDIT_TODO)) //the send method needs to be executed in a coroutine/ asynchronously
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO)) //implement using coroutines
            }
            is TodoListEvent.OnUndoDeleteClick->{
                deletedTodo?.let { todo->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }

            }
            is TodoListEvent.OnDeleteTodoClick ->{
                viewModelScope.launch {
                    deletedTodo = event.todo // to be able to undo the delete we make sure we have cached it.
                    repository.deleteTodo(
                        event.todo  //to be able to undo the deletion
                    )
                    sendUiEvent(UiEvent.ShowSnackbar("Todo deleted", "Undo")) // end ui event
                }
            }
            is TodoListEvent.OnDoneChange->{
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                } // to perform a DB operation we need to do it using coroutines

            }
            is TodoListEvent.OnTodoClick->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO+"?todoId=${event.todo.id}"))

            }
        }
    }

//    sends  ui event
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event) //the send method needs to be executed in a coroutine/ asynchronously
        } // viewmodelscope is, used to bind lifetime of coroutine to viewmodel lifecycle
    }
}