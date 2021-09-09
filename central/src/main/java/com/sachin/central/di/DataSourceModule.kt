package com.sachin.central.di

import android.content.Context
import com.sachin.central.BuildConfig
import com.sachin.central.BuildConfig.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

const val CAT_RETROFIT_NAME = "catNetworkInterface"
const val DOG_RETROFIT_NAME = "dogNetworkInterface"

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {

  val dogApiBaseUrl = "\"https://dog-facts-api.herokuapp.com/\""
  val catApiBaseUrl = "\"https://www.DEV_URL.com/\""


  @Singleton
  @Provides
  fun providesDogFactsRetrofit(
    @ApplicationContext context: Context
  ): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient().newBuilder()
    httpClient.addInterceptor(logging)

    return Retrofit.Builder().baseUrl(dogApiBaseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient.build())
      .build()
  }

  @Singleton
  @Provides
  @Named(CAT_RETROFIT_NAME)
  fun provideCatFactsRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient().newBuilder()
    httpClient.addInterceptor(logging)

    return Retrofit.Builder().baseUrl(catApiBaseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient.build())
      .build()
  }

}


