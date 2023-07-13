package com.ewide.test.dimasaryamurdiyan.di

import android.content.Context
import androidx.room.Room
import com.ewide.test.dimasaryamurdiyan.data.source.local.preference.IPreference
import com.ewide.test.dimasaryamurdiyan.data.source.local.preference.Preference
import com.ewide.test.dimasaryamurdiyan.data.source.local.room.GameDatabase
import com.ewide.test.dimasaryamurdiyan.data.source.local.room.dao.GameDao
import com.ewide.test.dimasaryamurdiyan.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase = Room.databaseBuilder(
        context,
        GameDatabase::class.java, Constants.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: GameDatabase): GameDao = database.gameDao()

    @Provides
    @PreferenceInfo
    internal fun providePrefFileName(): String = Constants.PREF_FILE_NAME

    @Provides
    @Singleton
    internal fun providePreference(preference: Preference): IPreference = preference
}