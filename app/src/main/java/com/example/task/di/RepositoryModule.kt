package com.example.task.di

import com.example.task.data.remote.ApiEndPoints
import com.example.task.data.reposirories.elixirs.ElixirsDataSource
import com.example.task.data.reposirories.elixirs.ElixirsDataSourceImpl
import com.example.task.data.reposirories.wizard.WizardDataSource
import com.example.task.data.reposirories.wizard.WizardDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideElixirsRepository(elixirsDataSourceImpl: ElixirsDataSourceImpl): ElixirsDataSource

    @Singleton
    @Binds
    abstract fun provideWizardsRepository(wizardsDataSourceImpl: WizardDataSourceImpl): WizardDataSource
}