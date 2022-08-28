package com.mahmoudalim.onboarding_domain.di

import com.mahmoudalim.onboarding_domain.usecase.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * @author Mahmoud Alim on 27/08/2022.
 */

@Module()
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNutrientsUSeCase() = ValidateNutrients()
}