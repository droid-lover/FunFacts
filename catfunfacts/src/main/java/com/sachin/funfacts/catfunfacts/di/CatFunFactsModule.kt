package com.sachin.funfacts.catfunfacts.di

import com.sachin.central.datasource.CatFactsDataSource
import com.sachin.funfacts.catfunfacts.repository.CatFactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
@Module
@InstallIn(ApplicationComponent::class)
class CatFunFactsModule {

  @Singleton
  @Provides
  fun provideCatFactsRepository(
          catFactsDataSource: CatFactsDataSource
  ): CatFactsRepository = CatFactsRepository(catFactsDataSource)


}
