package com.mahmoudalim.tracker_domain.di

import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.tracker_domain.repository.TrackerRepo
import com.mahmoudalim.tracker_domain.usecase.CalculateMealNutrientsUseCase
import com.mahmoudalim.tracker_domain.usecase.DeleteTrackedFoodUseCase
import com.mahmoudalim.tracker_domain.usecase.GetFoodForDateUseCase
import com.mahmoudalim.tracker_domain.usecase.SearchFoodUseCase
import com.mahmoudalim.tracker_domain.usecase.TrackFoodUseCase
import com.mahmoudalim.tracker_domain.usecase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * @author Mahmoud Alim on 15/10/2022.
 */

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        pref: Preferences,
        repo: TrackerRepo
    ) = TrackerUseCases(
        calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(pref),
        deleteTrackedFoodUseCase = DeleteTrackedFoodUseCase(repo),
        getFoodForDateUseCase = GetFoodForDateUseCase(repo),
        trackedFoodUseCase = TrackFoodUseCase(repo),
        searchForFoodUseCase = SearchFoodUseCase(repo),
    )
}