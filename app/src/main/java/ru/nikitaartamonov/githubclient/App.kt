package ru.nikitaartamonov.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.nikitaartamonov.githubclient.data.retrofit.GithubLoaderRetrofit
import ru.nikitaartamonov.githubclient.domain.UserNamesCollection
import ru.nikitaartamonov.githubclient.impl.MockUserNamesCollection

class App : Application() {

    val usersList: UserNamesCollection by lazy {
        MockUserNamesCollection(mutableListOf("ArtNikita", "kirich1409", "JakeWharton", "kshalnov"))
    }

    val githubLoader by lazy { GithubLoaderRetrofit() }

}

val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app