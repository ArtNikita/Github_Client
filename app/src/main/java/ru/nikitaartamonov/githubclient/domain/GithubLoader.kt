package ru.nikitaartamonov.githubclient.domain

import io.reactivex.rxjava3.core.Observable

interface GithubLoader {

    fun loadUser(userName: String): Observable<Result>
    fun loadRepos(userName: String): Observable<Result>

    sealed class Result {
        data class Error(val error: Throwable) : Result()
        data class Success<T>(val body: T) : Result()
    }
}