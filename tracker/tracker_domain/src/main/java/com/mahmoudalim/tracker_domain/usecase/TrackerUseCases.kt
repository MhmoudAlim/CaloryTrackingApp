package com.mahmoudalim.tracker_domain.usecase

/**
 * @author Mahmoud Alim on 15/10/2022.
 */
data class TrackerUseCases(
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase,
    val deleteTrackedFoodUseCase: DeleteTrackedFoodUseCase,
    val getFoodForDateUseCase: GetFoodForDateUseCase,
    val trackedFoodUseCase: TrackFoodUseCase,
    val searchForFoodUseCase: SearchFoodUseCase,
)
