package ru.nikitaartamonov.githubclient.ui.pages.users_list

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.githubclient.domain.Event

interface UsersListContract {

    interface ViewModel {

        val openUserPageLiveData: LiveData<Event<String>>

        fun onUserClick(userName: String)
    }
}