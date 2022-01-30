package ru.nikitaartamonov.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class App : Application() {

    val usersList: List<String> by lazy {
        listOf("ArtNikita", "kirich1409", "JakeWharton", "kshalnov")
    }

}

val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app