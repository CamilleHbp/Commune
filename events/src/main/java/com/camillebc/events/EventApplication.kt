package com.camillebc.events

import android.app.Application
import com.camillebc.events.di.DaggerEventComponent
import com.camillebc.events.di.EventComponent
import com.camillebc.events.di.EventModule

class EventApplication: Application() {
    lateinit var eventComponent: EventComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        eventComponent = DaggerEventComponent.builder()
            .eventModule(EventModule(this))
            .build()
    }

    companion object {
        private var INSTANCE: EventApplication? = null

        @JvmStatic
        fun get(): EventApplication = INSTANCE!!
    }
}