package com.camillebc.commune.events.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camillebc.commune.utilities.dateString
import java.util.*

class EventViewModel: ViewModel() {
    val startDate: MutableLiveData<String> = MutableLiveData(Calendar.getInstance().dateString())
    val endDate: MutableLiveData<String> = MutableLiveData(Calendar.getInstance().dateString())
}