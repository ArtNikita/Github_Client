package ru.nikitaartamonov.githubclient.di

import dagger.Module
import dagger.Provides
import ru.nikitaartamonov.githubclient.domain.UserNamesCollection
import ru.nikitaartamonov.githubclient.impl.MockUserNamesCollection
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideUsersList(): UserNamesCollection =
        MockUserNamesCollection(mutableListOf("ArtNikita", "kirich1409", "JakeWharton", "kshalnov"))
}