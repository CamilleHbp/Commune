package com.camillebc.commune

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.camillebc.androidutils.extensions.addFragment
import com.camillebc.commune.events.data.EventViewModel
import com.camillebc.commune.events.fragments.EventEditorFragment

class MainActivity : AppCompatActivity() {
    private lateinit var eventViewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        val eventEditorFragment = EventEditorFragment()
        addFragment(eventEditorFragment, R.id.mainFragment)
    }
}
