package ru.nikitaartamonov.githubclient.impl

import ru.nikitaartamonov.githubclient.domain.UserNamesCollection

class MockUserNamesCollection(private val userNamesList: MutableList<String> = mutableListOf()) :
    UserNamesCollection {

    override fun addUser(userName: String) {
        userNamesList += userName
    }

    override fun getUser(position: Int): String? {
        if (position < 0 || position >= size) return null
        return userNamesList[position]
    }

    override fun removeUser(userName: String): Boolean {
        return userNamesList.remove(userName)
    }

    override fun getAllUserNames(): List<String> {
        return ArrayList(userNamesList)
    }

    override val size: Int
        get() = userNamesList.size
}