package com.camillebc.commune.events.di

import com.camillebc.commune.MainActivity
import com.camillebc.fusy.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    EventDatabaseModule::class
])
interface EventComponent {
    fun inject(mainActivity: MainActivity)
}
