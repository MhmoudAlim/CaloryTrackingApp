package com.mahmoudalim.tracker_domain.usecase

import com.mahmoudalim.tracker_domain.model.TrackedFood
import com.mahmoudalim.tracker_domain.repository.TrackerRepo

/**
 * @author Mahmoud Alim on 14/10/2022.
 */
class DeleteTrackedFoodUseCase(private val repo: TrackerRepo) {

    suspend operator fun invoke(food: TrackedFood) = repo.deleteTrackedFood(food)

}