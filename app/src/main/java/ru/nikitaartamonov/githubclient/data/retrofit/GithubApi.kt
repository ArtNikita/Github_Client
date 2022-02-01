package ru.nikitaartamonov.githubclient.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

interface GithubApi {

    @GET("users/{user}")
    fun getUser(@Path("user") userName: String) : Call<UserEntity>

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") userName: String) : Call<List<RepoEntity>>
}