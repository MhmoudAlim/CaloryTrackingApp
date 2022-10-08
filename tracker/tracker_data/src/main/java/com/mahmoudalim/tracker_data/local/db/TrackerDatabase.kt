package com.mahmoudalim.tracker_data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoudalim.tracker_data.local.dao.TrackerDao
import com.mahmoudalim.tracker_data.local.entity.TrackedFoodEntity

/**
 * @author Mahmoud Alim on 08/10/2022.
 */

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase : RoomDatabase() {

    abstract val dao: TrackerDao


    companion object{
        const val NAME = "tracker_db"
    }
}