package com.camillebc.commune.events.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.camillebc.androidutils.extensions.dateStringToDate
import com.camillebc.commune.R
import com.camillebc.commune.events.EventConstants
import com.camillebc.commune.events.data.EventViewModel
import kotlinx.android.synthetic.main.fragment_event_editor.*
import java.util.*


/**
 * A simple [Fragment] subclass. This fragment is responsible for everything related to an event.
 *
 */
class EventEditorFragment: Fragment() {
    private lateinit var eventViewModel: EventViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        eventViewModel = activity?.let {
            ViewModelProviders.of(it).get(EventViewModel::class.java)
        } ?: throw Exception("EventViewModel: Invalid Activity")

        eventStartDate.text = eventViewModel.startDate.value
        eventEndDate.text = eventViewModel.endDate.value
        eventViewModel.startDate.observe(this, Observer<String> { dateString ->
            eventStartDate.text = dateString
        })
        eventViewModel.endDate.observe(this, Observer<String> { dateString ->
            eventEndDate.text = dateString
        })
        // Set all the onClick listeners
        eventStartDate.setOnClickListener { v ->
            pickDate(v)
        }
        eventEndDate.setOnClickListener { v ->
            pickDate(v)
        }
        addEvent.setOnClickListener { v ->
            addEvent(v)
        }
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_editor, container, false)
    }

    private fun pickDate(view: View?) {
        var datePickerFragment = when (view) {
            eventStartDate -> DatePickerFragment.newInstance(
                EventConstants.StartOrEnd.START,
                eventViewModel.startDate.value!!
            )
            eventEndDate -> DatePickerFragment.newInstance(
                EventConstants.StartOrEnd.END,
                eventViewModel.endDate.value!!
            )
            else -> throw Exception("DataPickerFragment: Invalid view")
        }
        datePickerFragment.show(activity?.supportFragmentManager!!, "datePicker")
    }

    private fun addEvent(view: View?) {
        val startMillis = Calendar.getInstance().run {
            time = dateStringToDate(eventViewModel.startDate.value!!)
            timeInMillis
        }

        val endMillis = Calendar.getInstance().run {
            time = dateStringToDate(eventViewModel.endDate.value!!)
            timeInMillis
        }
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            .putExtra(CalendarContract.Events.TITLE, eventTitle.text.toString())
            .putExtra(CalendarContract.Events.DESCRIPTION, eventDescription.text.toString())
            .putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation.text.toString())
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
            .putExtra(Intent.EXTRA_EMAIL, "")
        startActivity(intent)
    }
}
