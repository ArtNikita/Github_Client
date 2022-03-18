package ru.nikitaartamonov.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import ru.nikitaartamonov.githubclient.di.AppComponent
import ru.nikitaartamonov.githubclient.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}

val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app

val AndroidViewModel.app
    get() = getApplication<App>()