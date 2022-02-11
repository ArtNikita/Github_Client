package ru.nikitaartamonov.githubclient.domain

import io.reactivex.rxjava3.core.Single

interface GithubLoader {

    fun loadUser(userName: String): Single<Result>
    fun loadRepos(userName: String): Single<Result>

    sealed class Result {
        data class Error(val error: Throwable) : Result()
        data class Success<T>(val body: T) : Result()
    }
}