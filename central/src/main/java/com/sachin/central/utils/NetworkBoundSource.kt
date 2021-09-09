package com.sachin.central.utils

import androidx.annotation.WorkerThread
import com.google.gson.reflect.TypeToken
import com.sachin.central.datasource.ResourceState
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

/**
 * A repository which provides resource from remote end point.
 *
 * [API_RESPONSE_TYPE] represents the type for network.
 * [MAPPED_RETURN_TYPE] represents the final type to be mapped into before returning.
 */
abstract class NetworkBoundSource<API_RESPONSE_TYPE, MAPPED_RETURN_TYPE> {

    /* @Inject
     lateinit var changePassWordModuleRoute: ChangePasswordRoute*/

    fun asFlow() = flow<ResourceState<MAPPED_RETURN_TYPE>> {

        // Emit Loading State
        emit(ResourceState.loading())

        try {
            // Fetch latest data from server
            val apiResponse = fetchFromRemote()

            // Parse body
            val remoteData = apiResponse.body()

            // Check for response
            if (apiResponse.isSuccessful && remoteData != null) {
                // Emit success state with data
                emit(ResourceState.success(postProcess(remoteData)))
            } else {

                val type = object : TypeToken<String>() {}.type

                /* val errorResponse: ErrorModel? =
                   Gson().fromJson(apiResponse.errorBody()?.charStream(), type)*/

                /*  val errorResponse: ErrorModel? =
                    Gson().fromJson(apiResponse.errorBody()?.charStream(), type)*/


                var errorResponse: String? = null
                try {
                    if (apiResponse.errorBody() != null) {
                        errorResponse = apiResponse.errorBody().toString()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }


                // Emit Error state

                emit(ResourceState.error("$errorResponse", "errorData", errorResponse))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Emit Exception occurred
            emit(ResourceState.error("Network error!", "Can't get latest data.", null))
        }
    }

    /**
     * Fetches [Response] from the remote end point.
     */
    @WorkerThread
    protected abstract suspend fun fetchFromRemote(): Response<API_RESPONSE_TYPE>

    /**
     * Maps the [API_RESPONSE_TYPE] to a required [MAPPED_RETURN_TYPE].
     */
    @WorkerThread
    protected abstract suspend fun postProcess(originalData: API_RESPONSE_TYPE): MAPPED_RETURN_TYPE

}