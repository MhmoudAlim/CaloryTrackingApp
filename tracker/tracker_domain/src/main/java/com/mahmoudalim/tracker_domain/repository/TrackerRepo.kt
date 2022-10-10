package com.mahmoudalim.tracker_domain.repository

import com.mahmoudalim.tracker_domain.model.TrackableFood
import com.mahmoudalim.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 10/10/2022.
 */
interface TrackerRepo {

    suspend fun searchForFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>


    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    suspend fun getFoodForDate(date: LocalDate): Flow<List<TrackedFood>>

}