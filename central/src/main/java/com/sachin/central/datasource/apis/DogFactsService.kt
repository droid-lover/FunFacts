package com.sachin.central.datasource.apis

import com.sachin.central.datasource.pojos.DogFactsResModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DogFactsService {

    @GET("api/v1/resources/dogs")
    suspend fun getDogFunFacts(@Query("number") number: String): Response<ArrayList<DogFactsResModel>>


}