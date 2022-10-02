package com.mahmoudalim.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Mahmoud Alim on 02/10/2022.
 */

@Entity
data class TrackedFoodEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val carbs: String,
    val protein: String,
    val fat: String,
    val imageUrl: String?,
    val type: String,
    val amount: String,
    val calories: String,
    val dayOfMonths: Int,
    val month: Int,
    val year: Int,
)