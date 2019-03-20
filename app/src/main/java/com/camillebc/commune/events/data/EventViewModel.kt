package com.camillebc.commune.events.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventViewModel: ViewModel() {
    val startDate: MutableLiveData<String> = MutableLiveData()
    val endDate: MutableLiveData<String> = MutableLiveData()
}