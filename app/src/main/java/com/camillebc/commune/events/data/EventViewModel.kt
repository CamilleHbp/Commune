package com.camillebc.commune.events.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camillebc.androidutils.extensions.dateString
import com.camillebc.androidutils.extensions.timeString
import java.util.*

class EventViewModel: ViewModel() {
    private val cal = Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 8); set(Calendar.MINUTE, 0) }

    val startDate: MutableLiveData<String> = MutableLiveData(cal.dateString())
    val endDate: MutableLiveData<String> = MutableLiveData(cal.dateString())
    val startTime: MutableLiveData<String> = MutableLiveData(cal.timeString())
    val endTime: MutableLiveData<String> = MutableLiveData(cal.timeString())
}