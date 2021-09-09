package com.sachin.central.datasource

/**
 * Data and UI State management
 */
sealed class ResourceState<T> {


  class Loading<T> : ResourceState<T>()

  data class Success<T>(val data: T) : ResourceState<T>()

  data class Error<T>(val message: String, val data: String, val error: String?) :
    ResourceState<T>()

  companion object {

    /**
     * Returns [ResourceState.Loading] instance.
     */
    fun <T> loading() =
      Loading<T>()

    /**
     * Returns [ResourceState.Success] instance.
     * @param data Data to emit with status.
     */
    fun <T> success(data: T) =
      Success(data)

    /**
     * Returns [ResourceState.Error] instance.
     * @param message Description of failure.
     */
    fun <T> error(message: String, data: String, error: String?) =
      Error<T>(
        message,
        data,
        error
      )
  }

}