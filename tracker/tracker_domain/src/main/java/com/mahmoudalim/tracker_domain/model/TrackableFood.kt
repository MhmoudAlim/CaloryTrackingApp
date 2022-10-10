package com.mahmoudalim.tracker_domain.model

/**
 * @author Mahmoud Alim on 10/10/2022.
 */
data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100gm: Int,
    val carbsPer100gm: Int,
    val fatPer100gm: Int,
    val proteinPer100gm: Int,
)
