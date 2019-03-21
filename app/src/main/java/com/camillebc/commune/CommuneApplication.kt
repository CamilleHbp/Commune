package com.camillebc.commune

import android.app.Application
import com.camillebc.commune.events.di.DaggerEventComponent
import com.camillebc.commune.events.di.EventComponent
import com.camillebc.commune.di.modules.AppModule

class CommuneApplication: Application() {
    lateinit var eventComponent: EventComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        eventComponent = DaggerEventComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        private var INSTANCE: CommuneApplication? = null

        @JvmStatic
        fun get(): CommuneApplication = INSTANCE!!
    }
}