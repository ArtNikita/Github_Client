package ru.nikitaartamonov.githubclient.data

import io.reactivex.rxjava3.core.Single
import ru.nikitaartamonov.githubclient.domain.UserNamesRepo

class MockUserNamesRepo(private val userNamesList: MutableList<String> = mutableListOf()) :
    UserNamesRepo {

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

    override fun getAllUserNames(): Single<List<String>> {
        return Single.just(ArrayList(userNamesList))
    }

    override val size: Int
        get() = userNamesList.size
}