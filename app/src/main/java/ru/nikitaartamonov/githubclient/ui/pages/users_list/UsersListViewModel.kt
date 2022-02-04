package ru.nikitaartamonov.githubclient.ui.pages.users_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.githubclient.domain.Event

class UsersListViewModel : ViewModel(), UsersListContract.ViewModel {

    override val openUserPageLiveData: LiveData<Event<String>> = MutableLiveData()

    override fun onUserClick(userName: String) {
        openUserPageLiveData.postValue(Event(userName))
    }
}

private fun <T> LiveData<T>.postValue(data: T) {
    (this as MutableLiveData).postValue(data)
}