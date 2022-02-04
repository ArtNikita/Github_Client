package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nikitaartamonov.githubclient.App
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

class UserPageViewModel(application: Application) : AndroidViewModel(application),
    UserPageContract.ViewModel {

    private var userEntity: UserEntity? = null
    private var userRepos: List<RepoEntity>? = null

    override val renderUserLiveData: LiveData<UserEntity> = MutableLiveData()
    override val renderReposLiveData: LiveData<List<RepoEntity>> = MutableLiveData()

    override fun onViewIsReady(userName: String) {
        val userEntityCache = userEntity
        if (userEntityCache == null) {
            loadUser(userName)
        } else {
            renderUserLiveData.postValue(userEntityCache)
        }
        val userReposCache = userRepos
        if (userReposCache == null) {
            loadRepos(userName)
        } else {
            renderReposLiveData.postValue(userReposCache)
        }
    }

    private fun loadRepos(userName: String) {
        getApplication<App>().githubLoader.loadRepos(userName).subscribe { result ->
            when (result) {
                is GithubLoader.Result.Error -> {
                    //todo notify about error
                }
                is GithubLoader.Result.Success<*> -> {
                    userRepos = result.body as List<RepoEntity>
                    renderReposLiveData.postValue(result.body)
                }
            }
        }
    }

    private fun loadUser(userName: String) {
        getApplication<App>().githubLoader.loadUser(userName).subscribe { result ->
            when (result) {
                is GithubLoader.Result.Error -> {
                    //todo notify about error
                }
                is GithubLoader.Result.Success<*> -> {
                    userEntity = result.body as UserEntity
                    renderUserLiveData.postValue(result.body)
                }
            }
        }
    }
}

private fun <T> LiveData<T>.postValue(data: T) {
    (this as MutableLiveData).postValue(data)
}