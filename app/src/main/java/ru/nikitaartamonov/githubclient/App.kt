package ru.nikitaartamonov.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class App : Application() {

}

val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireContext() as App

fun <T> ViewModel.post(liveData: LiveData<T>, data: T) {
    (liveData as MutableLiveData).postValue(data)
}