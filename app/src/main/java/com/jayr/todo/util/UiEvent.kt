package com.jayr.todo.util

sealed class UiEvent {
   object PopBackStack: UiEvent() //event we send to ui when we want to pop the backstack
   data class Navigate(val route:String): UiEvent() // when we want too navigate ot new string
   data class  ShowSnackbar( //show snackbar
       val message:String,
       val action: String?=null
   ): UiEvent()

}
