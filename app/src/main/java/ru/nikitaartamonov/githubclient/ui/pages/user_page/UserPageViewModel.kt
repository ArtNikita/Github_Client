package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import ru.nikitaartamonov.githubclient.App
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

class UserPageViewModel(application: Application) : AndroidViewModel(application),
    UserPageContract.ViewModel {

    private var userEntity: UserEntity? = null

    override fun onViewIsReady(userName: String) {
        if (userEntity == null) {
            loadUser(userName)
        }
    }

    private fun loadUser(userName: String) {
        getApplication<App>().githubLoader.loadUser(userName) { result ->
            when (result) {
                is GithubLoader.Result.Error -> {
                    //todo notify about error
                }
                is GithubLoader.Result.Success -> {
                    userEntity = result.userEntity
                    //todo process value
                }
            }
        }
    }
}