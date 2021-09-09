package com.sachin.central.datasource

import com.sachin.central.datasource.pojos.DogFactsResModel
import okhttp3.ResponseBody
import retrofit2.Response

interface DogFactsDataSource {

    suspend fun getDogFunFacts(number: String): Response<ArrayList<DogFactsResModel>>

}