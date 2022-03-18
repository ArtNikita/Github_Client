package ru.nikitaartamonov.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import org.koin.core.context.startKoin
import ru.nikitaartamonov.githubclient.di.dbModule
import ru.nikitaartamonov.githubclient.di.networkModule
import ru.nikitaartamonov.githubclient.di.utilsModule

class App : Application() {

//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.builder().build()
//    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dbModule, networkModule, utilsModule)
        }
    }

}

val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app

val AndroidViewModel.app
    get() = getApplication<App>()