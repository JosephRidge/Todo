package com.jayr.todo.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.todo.data.Todo
import com.jayr.todo.data.TodoRepository
import com.jayr.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//inject repository
@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle // key- value object containing a bunch of state variables
): ViewModel(){
//    Adding our states
    var todo by mutableStateOf<Todo?>(null)
    private set
    var title by mutableStateOf("")
    private set
    var description by mutableStateOf("")
    private set

//    /Events we send from our viewmodel to the ui
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow() // expose it as an immutable event

    init {
        val todoId= savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1){
            viewModelScope.launch {
                 repository.getTodoNyId(todoId)?.let { todo ->
                    title  = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo

                }

            }
        }

    }

    fun onEvent(event:)
}