package com.sachin.central.datasource.pojos

import com.google.gson.annotations.SerializedName
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
data class CatFactsResModel (
    @SerializedName("text")
    var fact: String? = null
)