package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nikitaartamonov.githubclient.App
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

class UserPageViewModel(application: Application) : AndroidViewModel(application),
    UserPageContract.ViewModel {

    private var userEntity: UserEntity? = null

    override val renderUserLiveData: LiveData<UserEntity> = MutableLiveData()

    override fun onViewIsReady(userName: String) {
        val userEntityCache = userEntity
        if (userEntityCache == null) {
            loadUser(userName)
        } else {
            renderUserLiveData.postValue(userEntityCache)
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
                    renderUserLiveData.postValue(result.userEntity)
                }
            }
        }
    }
}

private fun <T> LiveData<T>.postValue(data: T) {
    (this as MutableLiveData).postValue(data)
}