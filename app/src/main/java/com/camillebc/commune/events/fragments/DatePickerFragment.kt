package com.camillebc.commune.events.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.camillebc.commune.events.EventConstants
import com.camillebc.commune.events.data.EventViewModel
import java.text.SimpleDateFormat
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
        arguments?.let {
             startOrEnd = it.getInt(EventConstants.ARG_START_END)
        }
        val cal = Calendar.getInstance()
        // Use the current date as default
        return DatePickerDialog(activity, this,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
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
        val cal = Calendar.getInstance().also { it.set(year, month, dayOfMonth) }
        val dateString = SimpleDateFormat("EEE, d MMM yyyy").format(cal.time).capitalize()
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
        fun newInstance(startOrEnd: EventConstants.StartOrEnd) =
            DatePickerFragment().apply {
                arguments = Bundle().apply {
                    putInt(EventConstants.ARG_START_END, startOrEnd.value)
                }
            }
    }
}
