package ru.nikitaartamonov.githubclient.data.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import retrofit2.Response
import ru.nikitaartamonov.githubclient.domain.GithubLoaderUsecase
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity

class GithubLoaderRetrofit(private val githubApi: GithubApi) : GithubLoaderUsecase {

    override fun loadUser(userName: String): Single<GithubLoaderUsecase.Result> {
        return Single.create { emitter ->
            try {
                val response = githubApi.getUser(userName).execute()
                processResponse(emitter, response)
            } catch (e: Exception) {
                emitter.onSuccess(GithubLoaderUsecase.Result.Error(e))
            }
        }
    }

    override fun loadRepos(userName: String): Single<GithubLoaderUsecase.Result> {
        return Single.create { emitter ->
            try {
                val response = githubApi.getRepos(userName).execute()
                processResponse(emitter, response)
            } catch (e: Exception) {
                emitter.onSuccess(GithubLoaderUsecase.Result.Error(e))
            }
        }

    }

    override fun loadUserRx(userName: String): Single<UserEntity> = githubApi.getUserRx(userName)

    override fun loadReposRx(userName: String): Single<List<RepoEntity>> =
        githubApi.getReposRx(userName)

    private fun <T> processResponse(
        emitter: SingleEmitter<GithubLoaderUsecase.Result>, response: Response<T>
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                emitter.onSuccess(GithubLoaderUsecase.Result.Success(it))
            } ?: emitter.onSuccess(GithubLoaderUsecase.Result.Error(Throwable("Empty response")))
        } else {
            emitter.onSuccess(GithubLoaderUsecase.Result.Error(Throwable("Server problem")))
        }
    }
}