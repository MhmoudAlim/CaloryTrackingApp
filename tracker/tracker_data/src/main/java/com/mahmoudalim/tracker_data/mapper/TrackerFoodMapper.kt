package com.mahmoudalim.tracker_data.mapper

import com.mahmoudalim.tracker_data.remote.dto.Product
import com.mahmoudalim.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

/**
 * @author Mahmoud Alim on 10/10/2022.
 */

object TrackerFoodMapper : Mapper<Product, TrackableFood?> {
    override fun map(input: Product): TrackableFood? {
        return TrackableFood(
            name = input.productName ?: return null,
            caloriesPer100gm = input.nutriments.energyKcal100g.roundToInt(),
            carbsPer100gm = input.nutriments.carbohydrates100g.roundToInt(),
            fatPer100gm = input.nutriments.fat100g.roundToInt(),
            proteinPer100gm = input.nutriments.proteins100g.roundToInt(),
            imageUrl = input.imageFrontThumbUrl
        )
    }
}

/* this is just another more convenient way to map between the two objects */
fun Product.toTrackableFood(): TrackableFood? {
    return TrackableFood(
        name = productName ?: return null,
        caloriesPer100gm = nutriments.energyKcal100g.roundToInt(),
        carbsPer100gm = nutriments.carbohydrates100g.roundToInt(),
        fatPer100gm = nutriments.fat100g.roundToInt(),
        proteinPer100gm = nutriments.proteins100g.roundToInt(),
        imageUrl = imageFrontThumbUrl
    )
}