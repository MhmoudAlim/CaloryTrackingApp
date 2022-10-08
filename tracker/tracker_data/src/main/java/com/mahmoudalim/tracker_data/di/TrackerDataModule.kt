package com.mahmoudalim.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.mahmoudalim.tracker_data.local.db.TrackerDatabase
import com.mahmoudalim.tracker_data.remote.api.OpenFoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * @author Mahmoud Alim on 2/10/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder().apply {
            baseUrl(OpenFoodApi.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create())
            client(client)
        }
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            TrackerDatabase.NAME
        ).build()
    }
}

