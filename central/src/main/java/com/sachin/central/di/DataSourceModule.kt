package com.sachin.central.di

import android.content.Context
import com.sachin.central.datasource.CatFactsDataSource
import com.sachin.central.datasource.CatFunFactsDataSourceImpl
import com.sachin.central.datasource.DogFactsDataSource
import com.sachin.central.datasource.DogFunFcatsDataSourceImpl
import com.sachin.central.datasource.apis.CatFactsService
import com.sachin.central.datasource.apis.DogFactsService
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
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */

const val CAT_RETROFIT_NAME = "catNetworkInterface"
const val DOG_RETROFIT_NAME = "dogNetworkInterface"

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {

  val dogApiBaseUrl = "https://dog-facts-api.herokuapp.com"
  val catApiBaseUrl = "https://cat-fact.herokuapp.com"


  @Singleton
  @Provides
  @Named(DOG_RETROFIT_NAME)
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


  @Singleton
  @Provides
  fun provideDogsFactsService(@Named(DOG_RETROFIT_NAME) retrofit: Retrofit): DogFactsService {
    return retrofit.create(DogFactsService::class.java)
  }

  @Singleton
  @Provides
  fun providesDogFactsDataSource(
    dogFactsService: DogFactsService
  ): DogFactsDataSource = DogFunFcatsDataSourceImpl(dogFactsService)


  @Singleton
  @Provides
  fun provideCatsFactsService(@Named(CAT_RETROFIT_NAME) retrofit: Retrofit): CatFactsService {
    return retrofit.create(CatFactsService::class.java)
  }

  @Singleton
  @Provides
  fun providesCatsFactsDataSource(
    catFactsService: CatFactsService
  ): CatFactsDataSource = CatFunFactsDataSourceImpl(catFactsService)



}


