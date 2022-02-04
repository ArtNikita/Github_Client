package ru.nikitaartamonov.githubclient.domain

interface UserNamesCollection {

    fun addUser(userName: String)
    fun getUser(position: Int): String?
    fun removeUser(userName: String): Boolean
    fun getAllUserNames(): List<String>
    val size: Int
}