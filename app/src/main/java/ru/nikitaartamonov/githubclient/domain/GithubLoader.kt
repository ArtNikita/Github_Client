package ru.nikitaartamonov.githubclient.domain

interface GithubLoader {

    fun loadUser(userName: String, callback: (Result) -> Unit)
    fun loadRepos(userName: String, callback: (Result) -> Unit)

    sealed class Result {
        data class Error(val error: Throwable) : Result()
        data class Success<T>(val body: T) : Result()
    }
}