package com.sachin.central.datasource.pojos

import com.google.gson.annotations.SerializedName
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
data class DogFactsResModel (
    @SerializedName("fact")
    var fact: String? = null
)