package com.mahmoudalim.tracker_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmoudalim.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Mahmoud Alim on 08/10/2022.
 */

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query("SELECT * FROM TrackedFoodEntity WHERE dayOfMonths = :dayOfMonth AND month = :month AND year = :year")
    fun getFoodFromDate(dayOfMonth: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}