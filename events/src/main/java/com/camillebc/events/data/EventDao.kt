package com.camillebc.events.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EventDao {
    @Query("select * from event")
    fun getAllEvents(): List<Event>

    @Query("select * from event where community_id = :communityId")
    fun getCommunityEvents(communityId: Long): List<Event>
}