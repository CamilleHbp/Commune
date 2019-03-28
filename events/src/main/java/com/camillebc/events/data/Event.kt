package com.camillebc.events.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    var name: String,
    val beginTime: Long,
    val endTime: Long,
    //val recurrence: Date,
    @ColumnInfo(name = "community_id")
    var communityId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}