package com.sachin.central.datasource

import com.sachin.central.datasource.pojos.CatFactsResModel
import com.sachin.central.datasource.pojos.DogFactsResModel
import okhttp3.ResponseBody
import retrofit2.Response
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
interface CatFactsDataSource {

    suspend fun getCatFunFacts(number: Int): Response<ArrayList<CatFactsResModel>>

}