package ru.nikitaartamonov.githubclient.ui.pages.user_page

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

interface UserPageContract {

    interface ViewModel {
        val renderUserLiveData: LiveData<UserEntity>

        fun onViewIsReady(userName: String)
    }
}