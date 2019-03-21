package com.camillebc.commune.utilities

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.dateFormat(): SimpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy")
fun Calendar.dateString(): String = this.dateFormat().format(this.time).capitalize()
fun Calendar.dateStringToDate(dateString: String) = dateFormat().parse(dateString)
