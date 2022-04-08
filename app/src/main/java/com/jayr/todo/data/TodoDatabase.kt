package com.jayr.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (
    entities = [Todo::class], //all tables within the db
    version = 1    // important when changing the db to enable easy migration
)

abstract class TodoDatabse: RoomDatabase(){

    abstract val dao : TodoDao
}