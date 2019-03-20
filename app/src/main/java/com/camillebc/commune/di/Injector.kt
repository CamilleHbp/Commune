package com.camillebc.fusy.di

import com.camillebc.commune.CommuneApplication
import com.camillebc.commune.events.di.EventComponent

class Injector private constructor() {
    companion object {
        var fictionComponent: EventComponent = CommuneApplication.get().eventComponent
    }
}