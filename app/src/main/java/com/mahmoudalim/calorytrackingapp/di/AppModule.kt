package com.mahmoudalim.calorytrackingapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.mahmoudalim.core.data.preferences.DefaultPreferences
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.PREF_NAME
import com.mahmoudalim.core.domian.usecase.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mahmoud Alim on 19/05/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences =
        DefaultPreferences(sharedPref = sharedPreferences)

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase() = FilterOutDigits()
}