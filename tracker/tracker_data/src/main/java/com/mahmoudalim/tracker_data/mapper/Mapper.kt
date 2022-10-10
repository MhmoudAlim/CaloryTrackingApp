package com.mahmoudalim.tracker_data.mapper

/**
 * @author Mahmoud Alim on 10/10/2022.
 */

interface Mapper<I, O> {

    fun map(input: I): O
}