package ru.nikitaartamonov.githubclient.di

import dagger.Component
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import ru.nikitaartamonov.githubclient.domain.UserNamesCollection
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [DbModule::class, NetworkModule::class, UtilsModule::class])
interface AppComponent {

    fun getUsersList(): UserNamesCollection

    fun getGithubLoader(): GithubLoader

    @Named("uuid")
    fun getUuid(): String
}