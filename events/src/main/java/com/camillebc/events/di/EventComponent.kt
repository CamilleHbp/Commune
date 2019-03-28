package com.camillebc.events.di

import com.camillebc.events.EventActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    EventModule::class,
    EventDatabaseModule::class
])
interface EventComponent {
    fun inject(eventActivity: EventActivity)
}
