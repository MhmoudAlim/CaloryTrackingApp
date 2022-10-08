package com.mahmoudalim.tracker_data.local.db

import androidx.room.Database
import com.mahmoudalim.tracker_data.local.dao.TrackerDao

/**
 * @author Mahmoud Alim on 08/10/2022.
 */

@Database(
    entities = [TrackerDatabase::class],
    version = 1
)
abstract class TrackerDatabase {

    abstract val dao: TrackerDao

}