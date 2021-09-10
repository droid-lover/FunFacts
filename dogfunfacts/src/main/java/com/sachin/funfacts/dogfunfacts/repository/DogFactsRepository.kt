package com.sachin.funfacts.dogfunfacts.repository


import com.sachin.central.datasource.DogFactsDataSource
import com.sachin.central.datasource.ResourceState
import com.sachin.central.datasource.pojos.DogFactsResModel
import com.sachin.central.utils.NetworkBoundSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class DogFactsRepository(
    private val dogFactsDataSource: DogFactsDataSource
) {

    fun getDogFunFacts(number: String): Flow<ResourceState<ArrayList<DogFactsResModel>>> {
        return object : NetworkBoundSource<ArrayList<DogFactsResModel>, ArrayList<DogFactsResModel>>() {

            override suspend fun fetchFromRemote(): Response<ArrayList<DogFactsResModel>> {

                return dogFactsDataSource.getDogFunFacts(number)
            }

            override suspend fun postProcess(originalData: ArrayList<DogFactsResModel>): ArrayList<DogFactsResModel> {
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }
}
