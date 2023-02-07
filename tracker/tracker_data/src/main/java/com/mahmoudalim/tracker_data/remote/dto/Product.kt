package com.mahmoudalim.tracker_data.remote.dto

import com.squareup.moshi.Json

/**
 * @author Mahmoud Alim on 27/09/2022.
 */

data class Product(
    @field:Json(name = "image_front_thumb_url")
    val imageFrontThumbUrl: String?,
    val nutriments: Nutriments,
    @field:Json(name = "product_name")
    val productName: String?
)