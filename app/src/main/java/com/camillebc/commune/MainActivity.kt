package com.camillebc.commune

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.camillebc.commune.events.data.EventViewModel
import com.camillebc.commune.events.fragments.EventEditorFragment
import com.camillebc.fusy.utilities.addFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

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
