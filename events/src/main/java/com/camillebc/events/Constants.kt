package com.camillebc.events

object Constants {
    enum class StartOrEnd(val value: Int) {
        START(1),
        END(2)
    }
    const val ARG_START_END = "StartOrEnd"
    const val ARG_YEAR = "Year"
    const val ARG_MONTH = "Month"
    const val ARG_DAY_OF_MONTH = "DayOFMonth"
    const val ARG_HOUR = "Hour"
    const val ARG_MINUTE = "Minute"
}