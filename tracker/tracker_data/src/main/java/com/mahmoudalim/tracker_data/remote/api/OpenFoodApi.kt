package com.mahmoudalim.tracker_data.remote.api

import com.mahmoudalim.tracker_data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Mahmoud Alim on 27/09/2022.
 */

interface OpenFoodApi {

    @GET(SEARCH_FOOD_ENDPOINT)
    suspend fun searchForFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): SearchDto

    companion object {
        const val BASE_URL = "https://us.openfoodfacts.org/"
        const val SEARCH_FOOD_ENDPOINT =
            "cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url"
    }
}