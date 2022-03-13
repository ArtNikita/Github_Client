package ru.nikitaartamonov.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.githubclient.data.retrofit.GithubApi
import ru.nikitaartamonov.githubclient.data.retrofit.GithubLoaderRetrofit
import ru.nikitaartamonov.githubclient.domain.UserNamesCollection
import ru.nikitaartamonov.githubclient.impl.MockUserNamesCollection
import java.util.*

class App : Application() {

    val usersList: UserNamesCollection by lazy {
        MockUserNamesCollection(mutableListOf("ArtNikita", "kirich1409", "JakeWharton", "kshalnov"))
    }

    val githubLoader by lazy { GithubLoaderRetrofit(githubApi) }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val githubApi: GithubApi by lazy { retrofit.create(GithubApi::class.java) }

    val uuid: String
        get() = UUID.randomUUID().toString()

}

val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app