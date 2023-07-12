package com.ewide.test.dimasaryamurdiyan.di

import com.ewide.test.dimasaryamurdiyan.data.GameRepository
import com.ewide.test.dimasaryamurdiyan.domain.repository.IGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: GameRepository): IGameRepository

}