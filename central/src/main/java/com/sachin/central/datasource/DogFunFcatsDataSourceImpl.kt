package com.sachin.central.datasource

import com.sachin.central.datasource.apis.DogFactsService
import com.sachin.central.datasource.pojos.DogFactsResModel
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
class DogFunFcatsDataSourceImpl @Inject constructor(private val dogFactsService: DogFactsService) :
        DogFactsDataSource {

    override suspend fun getDogFunFacts(number: String): Response<ArrayList<DogFactsResModel>> =
        dogFactsService.getDogFunFacts(number)


}