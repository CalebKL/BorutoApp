package com.example.burutoapp.di

import android.content.Context
import com.example.burutoapp.data.repository.DataStoreOperationImp
import com.example.burutoapp.data.repository.Repository
import com.example.burutoapp.domain.repository.DataStoreOperations
import com.example.burutoapp.domain.use_case.UseCases
import com.example.burutoapp.domain.use_case.get_all_heroes.GetAllHeroesUseCase
import com.example.burutoapp.domain.use_case.get_selected_hero.GetSelectedHeroUseCase
import com.example.burutoapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.example.burutoapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase
import com.example.burutoapp.domain.use_case.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{
    @Provides
    @Singleton
    fun provideDataStoreOperation(
        @ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationImp(
            context = context
        )
    }
    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository)
        )
    }
}