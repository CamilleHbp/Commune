package com.camillebc.commune.events

object EventConstants {
    enum class StartOrEnd(val value: Int) {
        START(1), END(2)
    }
    const val ARG_START_END = "StartOrEnd"
}