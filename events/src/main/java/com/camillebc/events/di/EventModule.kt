package com.camillebc.events.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EventModule(private val appContext: Context) {
    @Provides @Singleton
    fun provideApplicationContext()= appContext
}