package me.communes.commune.data

import android.database.Cursor

data class Contact(val displayName: String = "Unknown", val phoneNumber: String = "Unknown"){
    fun getCursor(cursor: Cursor) {
        cursor.columnNames
    }
}