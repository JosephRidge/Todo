package com.jayr.todo.di

import android.app.Application
import androidx.room.Room
import com.jayr.todo.data.TodoDatabse
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

//    How daagger can create our room interface
    @Provides
    @Singleton
    fun provideTodoDatabase (app: Application):TodoDatabse{
        return Room.databaseBuilder(
            app,
            TodoDatabse::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db:TodoDatabse):TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }
}