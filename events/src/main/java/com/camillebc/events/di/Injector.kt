package com.camillebc.events.di

import com.camillebc.events.EventApplication

class Injector private constructor() {
    companion object {
        var eventComponent: EventComponent = EventApplication.get().eventComponent
    }
}