package com.example.testexercise.di

import com.example.testexercise.data.NetworkRepositoryImpl
import com.example.testexercise.domain.UseCase
import com.example.testexercise.domain.repository.NetworkRepository
import com.example.testexercise.domain.usecase.GetPaymentsUseCase
import com.example.testexercise.domain.usecase.PostRegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNetworkRepositoryImpl(repository: NetworkRepositoryImpl): NetworkRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideNetworkUseCases(networkRepository: NetworkRepository): UseCase {
        return UseCase(
            postRegistrationUseCase = PostRegistrationUseCase(networkRepository),
            getPaymentsUseCase = GetPaymentsUseCase(networkRepository)
        )
    }
}