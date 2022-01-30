package ru.nikitaartamonov.githubclient.domain

import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

interface GithubLoader {

    fun loadUser(userName: String, callback: (Result) -> Unit)

    sealed class Result {
        data class Error(val error: Throwable) : Result()
        data class Success(val userEntity: UserEntity) : Result()
    }
}