package ru.nikitaartamonov.githubclient.domain

import io.reactivex.rxjava3.core.Single
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

interface GithubLoader {

    fun loadUser(userName: String): Single<Result>
    fun loadRepos(userName: String): Single<Result>
    fun loadUserRx(userName: String): Single<UserEntity>
    fun loadReposRx(userName: String): Single<List<RepoEntity>>

    sealed class Result {
        data class Error(val error: Throwable) : Result()
        data class Success<T>(val body: T) : Result()
    }
}