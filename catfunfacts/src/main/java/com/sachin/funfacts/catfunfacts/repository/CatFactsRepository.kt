package com.sachin.funfacts.catfunfacts.repository


import com.sachin.central.datasource.CatFactsDataSource
import com.sachin.central.datasource.ResourceState
import com.sachin.central.datasource.pojos.CatFactsResModel
import com.sachin.central.utils.NetworkBoundSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
class CatFactsRepository(
    private val catFactsDataSource: CatFactsDataSource
) {

    fun getCatFunFacts(number: Int): Flow<ResourceState<ArrayList<CatFactsResModel>>> {
        return object : NetworkBoundSource<ArrayList<CatFactsResModel>, ArrayList<CatFactsResModel>>() {

            override suspend fun fetchFromRemote(): Response<ArrayList<CatFactsResModel>> {

                return catFactsDataSource.getCatFunFacts(number)
            }

            override suspend fun postProcess(originalData: ArrayList<CatFactsResModel>): ArrayList<CatFactsResModel> {
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }
}
