package ru.nikitaartamonov.githubclient.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.githubclient.data.retrofit.GithubApi
import ru.nikitaartamonov.githubclient.data.retrofit.GithubLoaderRetrofit
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Named("baseUrl")
    @Provides
    fun provideBaseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

    @Singleton
    @Provides
    fun provideGithubLoader(githubApi: GithubApi): GithubLoader = GithubLoaderRetrofit(githubApi)
}