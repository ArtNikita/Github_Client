package ru.nikitaartamonov.githubclient.data.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

class GithubLoaderRetrofit : GithubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubApi = retrofit.create(GithubApi::class.java)

    override fun loadUser(userName: String, callback: (GithubLoader.Result) -> Unit) {
        githubApi.getUser(userName).enqueue(object : Callback<UserEntity> {
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(GithubLoader.Result.Success(it))
                    } ?: callback(GithubLoader.Result.Error(Throwable("Empty response")))
                } else {
                    callback(GithubLoader.Result.Error(Throwable("Server problem")))
                }
            }

            override fun onFailure(call: Call<UserEntity>, error: Throwable) {
                callback(GithubLoader.Result.Error(error))
            }
        })
    }
}