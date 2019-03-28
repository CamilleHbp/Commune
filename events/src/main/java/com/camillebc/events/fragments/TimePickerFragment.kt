package com.camillebc.events.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.camillebc.androidutils.extensions.timeString
import com.camillebc.androidutils.extensions.timeStringToDate
import com.camillebc.events.data.EventViewModel
import java.util.*

/**
 * A [DialogFragment] subclass that return a [TimePickerDialog]
 * It implements a [TimePickerDialog.OnTimeSetListener] interface
 * to handle interaction events.
 *
 */
class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var eventViewModel: EventViewModel
    private var startOrEnd =
        com.camillebc.events.Constants.StartOrEnd.START.value

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        var minute = cal.get(Calendar.MINUTE)
        arguments?.let {
            startOrEnd = it.getInt(com.camillebc.events.Constants.ARG_START_END)
            hour = it.getInt(com.camillebc.events.Constants.ARG_HOUR)
            minute = it.getInt(com.camillebc.events.Constants.ARG_MINUTE)
        }
        // Use the current date as default
        return TimePickerDialog(activity, this, hour, minute, true)

    }

    /**
     * Get the activity's [EventViewModel] to update data
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        eventViewModel = activity?.let {
            ViewModelProviders.of(it).get(EventViewModel::class.java)
        } ?: throw Exception("EventViewModel: Invalid Activity")

        super.onActivityCreated(savedInstanceState)
    }

    /**
     * Update the activity's [EventViewModel] data
     *
     */
    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        val timeString = Calendar.getInstance().also {
            it.set(Calendar.HOUR_OF_DAY, hour )
            it.set(Calendar.MINUTE, minute) }.timeString()
        when (startOrEnd) {
            com.camillebc.events.Constants.StartOrEnd.START.value -> eventViewModel.startTime.postValue(timeString)
            com.camillebc.events.Constants.StartOrEnd.END.value -> eventViewModel.endTime.postValue(timeString)
        }
    }

    /**
     * This companion object allows to build an instance of the fragment with a parameter
     */
    companion object {
        @JvmStatic
        fun newInstance(startOrEnd: com.camillebc.events.Constants.StartOrEnd, timeString: String) =
            TimePickerFragment().apply {
                val calendar = Calendar.getInstance().also { it.time = it.timeStringToDate(timeString) }
                arguments = Bundle().apply {
                    putInt(com.camillebc.events.Constants.ARG_START_END, startOrEnd.value)
                    putInt(com.camillebc.events.Constants.ARG_HOUR, calendar.get(Calendar.HOUR_OF_DAY))
                    putInt(com.camillebc.events.Constants.ARG_MINUTE, calendar.get(Calendar.MINUTE))
                }
            }
    }
}

