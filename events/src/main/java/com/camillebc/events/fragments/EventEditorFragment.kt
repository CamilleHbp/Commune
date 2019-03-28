package com.camillebc.events.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.camillebc.androidutils.extensions.stringToDate
import com.camillebc.events.R
import com.camillebc.events.data.EventViewModel
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

        setObservers()
        setViews()
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_editor, container, false)
    }

    private fun addEvent(view: View?) {
        val startMillis = Calendar.getInstance().run {
            time = stringToDate(eventViewModel.startDate.value!!, eventViewModel.startTime.value!!)
            timeInMillis
        }

        val endMillis = Calendar.getInstance().run {
            time = stringToDate(eventViewModel.endDate.value!!, eventViewModel.endTime.value!!)
            timeInMillis
        }
        if ( endMillis <= startMillis ) {
            Toast.makeText(this.context, "The event cannot end BEFORE it starts!\n" +
                    "Please correct the event's dates.", Toast.LENGTH_SHORT).show()
            return
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

    private fun pickDate(view: View?) {
        var datePickerFragment = when (view) {
            eventStartDate -> DatePickerFragment.newInstance(
                com.camillebc.events.Constants.StartOrEnd.START,
                eventViewModel.startDate.value!!
            )
            eventEndDate -> DatePickerFragment.newInstance(
                com.camillebc.events.Constants.StartOrEnd.END,
                eventViewModel.endDate.value!!
            )
            else -> throw Exception("DataPickerFragment: Invalid view")
        }
        datePickerFragment.show(activity?.supportFragmentManager!!, "datePicker")
    }

    private fun pickTime(view: View?) {
        var timePickerFragment = when (view) {
            eventStartTime -> TimePickerFragment.newInstance(
                com.camillebc.events.Constants.StartOrEnd.START,
                eventViewModel.startTime.value!!
            )
            eventEndTime -> TimePickerFragment.newInstance(
                com.camillebc.events.Constants.StartOrEnd.END,
                eventViewModel.endTime.value!!
            )
            else -> throw Exception("DataPickerFragment: Invalid view")
        }
        timePickerFragment.show(activity?.supportFragmentManager!!, "timePicker")
    }

    /**
     * Set [EventViewModel] observers.
     */
    private fun setObservers() {
        eventViewModel.startDate.observe(this, Observer<String> { dateString ->
            eventStartDate.text = dateString
        })
        eventViewModel.endDate.observe(this, Observer<String> { dateString ->
            eventEndDate.text = dateString
        })
        eventViewModel.startTime.observe(this, Observer<String> { timeString ->
            eventStartTime.text = timeString
        })
        eventViewModel.endTime.observe(this, Observer<String> { timeString ->
            eventEndTime.text = timeString
        })
    }

    /**
     * set Views' values and onClick listeners.
     */
    private fun setViews() {
        // Set values
        eventStartDate.text = eventViewModel.startDate.value
        eventEndDate.text = eventViewModel.endDate.value
        eventStartTime.text = eventViewModel.startTime.value
        eventEndTime.text = eventViewModel.endTime.value
        // Set listeners
        eventStartDate.setOnClickListener { v ->
            pickDate(v)
        }
        eventEndDate.setOnClickListener { v ->
            pickDate(v)
        }
        eventStartTime.setOnClickListener { v ->
            pickTime(v)
        }
        eventEndTime.setOnClickListener { v ->
            pickTime(v)
        }
        addEvent.setOnClickListener { v ->
            addEvent(v)
        }
    }
}
