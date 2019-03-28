package com.camillebc.events.di

import android.content.Context
import androidx.room.Room
import com.camillebc.events.data.EventDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EventDatabaseModule {
    @Provides
    @Singleton
    fun providesEventDatabase(context: Context) =
        Room.databaseBuilder(context, EventDatabase::class.java, "event_db")
        .fallbackToDestructiveMigration()
        .build()
}