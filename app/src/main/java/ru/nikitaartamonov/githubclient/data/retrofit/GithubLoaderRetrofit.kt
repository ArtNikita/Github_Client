package ru.nikitaartamonov.githubclient.data.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.githubclient.domain.GithubLoader

class GithubLoaderRetrofit : GithubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubApi = retrofit.create(GithubApi::class.java)

    override fun loadUser(userName: String): Single<GithubLoader.Result> {
        return Single.create { emitter ->
            try {
                val response = githubApi.getUser(userName).execute()
                processResponse(emitter, response)
            } catch (e: Exception) {
                emitter.onSuccess(GithubLoader.Result.Error(e))
            }
        }
    }

    override fun loadRepos(userName: String): Single<GithubLoader.Result> {
        return Single.create { emitter ->
            try {
                val response = githubApi.getRepos(userName).execute()
                processResponse(emitter, response)
            } catch (e: Exception) {
                emitter.onSuccess(GithubLoader.Result.Error(e))
            }
        }

    }

    private fun <T> processResponse(
        emitter: SingleEmitter<GithubLoader.Result>, response: Response<T>
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                emitter.onSuccess(GithubLoader.Result.Success(it))
            } ?: emitter.onSuccess(GithubLoader.Result.Error(Throwable("Empty response")))
        } else {
            emitter.onSuccess(GithubLoader.Result.Error(Throwable("Server problem")))
        }
    }
}