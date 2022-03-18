package ru.nikitaartamonov.githubclient.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.githubclient.data.MockUserNamesRepo
import ru.nikitaartamonov.githubclient.data.retrofit.GithubApi
import ru.nikitaartamonov.githubclient.data.retrofit.GithubLoaderRetrofit
import ru.nikitaartamonov.githubclient.domain.GithubLoaderUsecase
import ru.nikitaartamonov.githubclient.domain.UserNamesRepo
import java.util.*

const val BASE_URL_QUALIFIER = "baseUrl"
const val UUID_QUALIFIER = "uuid"

val dbModule = module {
    single<UserNamesRepo> {
        MockUserNamesRepo(mutableListOf("ArtNikita", "kirich1409", "JakeWharton", "kshalnov"))
    }
}

val networkModule = module {
    single(named(BASE_URL_QUALIFIER)) { "https://api.github.com/" }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL_QUALIFIER)))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }

    single<GithubLoaderUsecase> { GithubLoaderRetrofit(get()) }
}

val utilsModule = module {
    factory(named(UUID_QUALIFIER)) { UUID.randomUUID().toString() }
}
