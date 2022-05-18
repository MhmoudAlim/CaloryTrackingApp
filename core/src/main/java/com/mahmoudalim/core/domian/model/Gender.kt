package com.mahmoudalim.core.domian.model

/**
 * Created by Mahmoud Alim on 18/05/2022.
 */

sealed class Gender(val name: String) {
    object Male : Gender(Type.MALE.value)
    object Female : Gender(Type.FEMALE.value)

    enum class Type(val value: String) {
        MALE("male"),
        FEMALE("female")
    }

    companion object {
        fun fromString(name: Type): Gender {
            return when (name) {
                Type.MALE -> Male
                Type.FEMALE -> Female
            }
        }
    }
}