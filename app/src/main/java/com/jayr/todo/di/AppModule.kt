package com.jayr.todo.di

import android.app.Application
import androidx.room.Room
import com.jayr.todo.data.TodoDatabase
import com.jayr.todo.data.TodoRepository
import com.jayr.todo.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//define dependencies needed
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    Repository instance
//    How dagger can create our room interface
    @Provides //provides a dependency
    @Singleton // single instance that will exist of it
    fun provideTodoDatabase (app: Application):TodoDatabase{// context -->  app
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db" //name of todoDB
        ).build()
    }

//Database Instance
    @Provides
    @Singleton
    fun provideTodoRepository(db:TodoDatabase):TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }
}