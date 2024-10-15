package com.example.task.di

import com.example.task.data.models.Elixir
import com.example.task.data.models.Wizard
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Wizard::class,
                Elixir::class,
            )
        )
            .schemaVersion(1)
            .name("default.realm")
            .deleteRealmIfMigrationNeeded()

        return Realm.open(config.build())
    }

}