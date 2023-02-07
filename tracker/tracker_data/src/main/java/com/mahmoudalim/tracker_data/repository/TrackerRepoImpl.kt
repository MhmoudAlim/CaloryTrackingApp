package com.mahmoudalim.tracker_data.repository

import com.mahmoudalim.core.NutrientsFacts.CARBS_CALORY_PER_GRAM
import com.mahmoudalim.core.NutrientsFacts.FAT_CALORY_PER_GRAM
import com.mahmoudalim.core.NutrientsFacts.PROTEIN_CALORY_PER_GRAM
import com.mahmoudalim.tracker_data.local.dao.TrackerDao
import com.mahmoudalim.tracker_data.mapper.toTrackableFood
import com.mahmoudalim.tracker_data.mapper.toTrackedFood
import com.mahmoudalim.tracker_data.mapper.toTrackedFoodEntity
import com.mahmoudalim.tracker_data.remote.api.OpenFoodApi
import com.mahmoudalim.tracker_data.remote.dto.Product
import com.mahmoudalim.tracker_domain.model.TrackableFood
import com.mahmoudalim.tracker_domain.model.TrackedFood
import com.mahmoudalim.tracker_domain.repository.TrackerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigInteger
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 10/10/2022.
 */
class TrackerRepoImpl(
    private val api: OpenFoodApi,
    private val dao: TrackerDao
) : TrackerRepo {
    override suspend fun searchForFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchForFood(query, page, pageSize)
            Result.success(searchDto.products
                .filter {
                    val (lowerBound, higherBound) = filterCorruptedProducts(it)
                    it.nutriments.energyKcal100g in (lowerBound..higherBound)
                }
                .mapNotNull { it.toTrackableFood() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private fun filterCorruptedProducts(it: Product): Pair<Double, Double> {
        val calculatedCalories =
            it.nutriments.carbohydrates100g.times(CARBS_CALORY_PER_GRAM) +
                    it.nutriments.proteins100g.times(PROTEIN_CALORY_PER_GRAM) +
                    it.nutriments.fat100g.times(FAT_CALORY_PER_GRAM)
        val lowerBound = calculatedCalories.times(0.9f)
        val higherBound = calculatedCalories.times(1.1f)
        return Pair(lowerBound, higherBound)
    }


    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodForDate(date: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodFromDate(
            dayOfMonth = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}