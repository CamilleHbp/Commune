package com.camillebc.events.data

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(
    entities = [Event::class],
    version = 1
)
abstract class EventDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}