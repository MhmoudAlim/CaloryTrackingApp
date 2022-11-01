package com.mahmoudalim.tracker_domain.usecase

import com.mahmoudalim.tracker_domain.model.TrackableFood
import com.mahmoudalim.tracker_domain.repository.TrackerRepo

/**
 * @author Mahmoud Alim on 14/10/2022.
 */
class SearchFoodUseCase(private val repo: TrackerRepo) {

    suspend operator fun invoke(
        query: String,
        pageNumber: Int = 1,
        pageSize: Int = 200
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) return Result.success(emptyList())

        val trimmedQuery = query.trim()
        return repo.searchForFood(trimmedQuery, pageNumber, pageSize)
    }

}
