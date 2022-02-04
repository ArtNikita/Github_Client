package ru.nikitaartamonov.githubclient.data.retrofit

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.githubclient.domain.GithubLoader
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

class GithubLoaderRetrofit : GithubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubApi = retrofit.create(GithubApi::class.java)

    override fun loadUser(userName: String): Observable<GithubLoader.Result> {
        val userSubject = BehaviorSubject.create<GithubLoader.Result>()
        githubApi.getUser(userName).enqueue(object : Callback<UserEntity> {
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        userSubject.onNext(GithubLoader.Result.Success(it))
                        userSubject.onComplete()

                    } ?: run {
                        userSubject.onNext(GithubLoader.Result.Error(Throwable("Empty response")))
                        userSubject.onComplete()
                    }
                } else {
                    userSubject.onNext(GithubLoader.Result.Error(Throwable("Server problem")))
                    userSubject.onComplete()
                }
            }

            override fun onFailure(call: Call<UserEntity>, error: Throwable) {
                userSubject.onNext(GithubLoader.Result.Error(error))
                userSubject.onComplete()
            }
        })
        return userSubject
    }

    override fun loadRepos(userName: String): Observable<GithubLoader.Result> {
        val reposSubject = BehaviorSubject.create<GithubLoader.Result>()
        githubApi.getRepos(userName).enqueue(object : Callback<List<RepoEntity>> {
            override fun onResponse(
                call: Call<List<RepoEntity>>, response: Response<List<RepoEntity>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        reposSubject.onNext(GithubLoader.Result.Success(it))
                        reposSubject.onComplete()
                    } ?: run {
                        reposSubject.onNext(GithubLoader.Result.Error(Throwable("Empty response")))
                        reposSubject.onComplete()
                    }
                } else {
                    reposSubject.onNext(GithubLoader.Result.Error(Throwable("Server problem")))
                    reposSubject.onComplete()
                }
            }

            override fun onFailure(call: Call<List<RepoEntity>>, error: Throwable) {
                reposSubject.onNext(GithubLoader.Result.Error(error))
                reposSubject.onComplete()
            }
        })
        return reposSubject
    }
}