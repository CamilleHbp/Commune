package com.camillebc.events

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.camillebc.androidutils.extensions.addFragment
import com.camillebc.events.data.EventViewModel
import com.camillebc.events.fragments.EventEditorFragment

class EventActivity : AppCompatActivity() {
    private lateinit var eventViewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        val eventEditorFragment = EventEditorFragment()
        addFragment(eventEditorFragment, R.id.eventsFragment)
    }
}
