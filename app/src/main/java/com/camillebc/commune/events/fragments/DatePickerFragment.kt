package com.camillebc.commune.events.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.camillebc.commune.events.EventConstants
import com.camillebc.commune.events.data.EventViewModel
import com.camillebc.commune.utilities.dateString
import com.camillebc.commune.utilities.dateStringToDate
import java.util.*

/**
 * A [DialogFragment] subclass that return a DateDialogPicker
 * It implements a [DatePickerDialog.OnDateSetListener] interface
 * to handle interaction events.
 *
 */
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var eventViewModel: EventViewModel
    private var startOrEnd = EventConstants.StartOrEnd.START.value

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()
        var year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)
        var dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        arguments?.let {
            startOrEnd = it.getInt(EventConstants.ARG_START_END)
            year = it.getInt(EventConstants.ARG_YEAR)
            month = it.getInt(EventConstants.ARG_MONTH)
            dayOfMonth = it.getInt(EventConstants.ARG_DAY_OF_MONTH)
        }
        // Use the current date as default
        return DatePickerDialog(activity, this, year, month, dayOfMonth)
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
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val dateString = Calendar.getInstance().also { it.set(year, month, dayOfMonth) }.dateString()
        when (startOrEnd) {
            EventConstants.StartOrEnd.START.value -> eventViewModel.startDate.postValue(dateString)
            EventConstants.StartOrEnd.END.value -> eventViewModel.endDate.postValue(dateString)
        }
    }

    /**
     * This companion object allows to build an instance of the fragment with a parameter
     */
    companion object {
        @JvmStatic
        fun newInstance(startOrEnd: EventConstants.StartOrEnd, dateString: String) =
            DatePickerFragment().apply {
                val calendar = Calendar.getInstance().also { it.time = it.dateStringToDate(dateString) }
                arguments = Bundle().apply {
                    putInt(EventConstants.ARG_START_END, startOrEnd.value)
                    putInt(EventConstants.ARG_YEAR, calendar.get(Calendar.YEAR))
                    putInt(EventConstants.ARG_MONTH, calendar.get(Calendar.MONTH))
                    putInt(EventConstants.ARG_DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
                }
            }
    }
}
