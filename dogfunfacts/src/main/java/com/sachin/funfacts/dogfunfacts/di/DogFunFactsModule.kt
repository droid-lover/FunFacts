package com.sachin.funfacts.dogfunfacts.di

import com.sachin.central.datasource.DogFactsDataSource
import com.sachin.funfacts.dogfunfacts.repository.DogFactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DogFunFactsModule {

  @Singleton
  @Provides
  fun provideDogFactsRepository(
          dogsFactsDataSource: DogFactsDataSource
  ): DogFactsRepository = DogFactsRepository(dogsFactsDataSource)


}
