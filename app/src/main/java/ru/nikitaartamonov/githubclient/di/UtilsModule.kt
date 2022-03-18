package ru.nikitaartamonov.githubclient.di

import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Named

@Module
class UtilsModule {

    @Named("uuid")
    @Provides
    fun provideUuid(): String = UUID.randomUUID().toString()
}