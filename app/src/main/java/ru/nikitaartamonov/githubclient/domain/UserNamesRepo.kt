package ru.nikitaartamonov.githubclient.domain

import io.reactivex.rxjava3.core.Single

interface UserNamesRepo {

    fun addUser(userName: String)
    fun getUser(position: Int): String?
    fun removeUser(userName: String): Boolean
    fun getAllUserNames(): Single<List<String>>
    val size: Int
}