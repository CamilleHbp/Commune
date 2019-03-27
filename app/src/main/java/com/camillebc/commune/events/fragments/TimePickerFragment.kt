package com.camillebc.commune.events.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.camillebc.androidutils.extensions.timeString
import com.camillebc.androidutils.extensions.timeStringToDate
import com.camillebc.commune.events.EventConstants
import com.camillebc.commune.events.data.EventViewModel
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
        EventConstants.StartOrEnd.START.value

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        var minute = cal.get(Calendar.MINUTE)
        arguments?.let {
            startOrEnd = it.getInt(EventConstants.ARG_START_END)
            hour = it.getInt(EventConstants.ARG_HOUR)
            minute = it.getInt(EventConstants.ARG_MINUTE)
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
            EventConstants.StartOrEnd.START.value -> eventViewModel.startTime.postValue(timeString)
            EventConstants.StartOrEnd.END.value -> eventViewModel.endTime.postValue(timeString)
        }
    }

    /**
     * This companion object allows to build an instance of the fragment with a parameter
     */
    companion object {
        @JvmStatic
        fun newInstance(startOrEnd: EventConstants.StartOrEnd, timeString: String) =
            TimePickerFragment().apply {
                val calendar = Calendar.getInstance().also { it.time = it.timeStringToDate(timeString) }
                arguments = Bundle().apply {
                    putInt(EventConstants.ARG_START_END, startOrEnd.value)
                    putInt(EventConstants.ARG_HOUR, calendar.get(Calendar.HOUR_OF_DAY))
                    putInt(EventConstants.ARG_MINUTE, calendar.get(Calendar.MINUTE))
                }
            }
    }
}

