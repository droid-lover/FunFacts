package com.sachin.central.datasource

import com.sachin.central.datasource.apis.CatFactsService
import com.sachin.central.datasource.apis.DogFactsService
import com.sachin.central.datasource.pojos.CatFactsResModel
import com.sachin.central.datasource.pojos.DogFactsResModel
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
class CatFunFactsDataSourceImpl @Inject constructor(private val catFactsService: CatFactsService) :
        CatFactsDataSource {

    override suspend fun getCatFunFacts(number: Int): Response<ArrayList<CatFactsResModel>> =
        catFactsService.getCatFunFacts("cat",number)

}