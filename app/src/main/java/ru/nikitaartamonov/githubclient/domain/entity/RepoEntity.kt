package ru.nikitaartamonov.githubclient.domain.entity

import com.google.gson.annotations.SerializedName

data class RepoEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("description")
    val description: String?,
    @SerializedName("fork")
    val isFork: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("pushed_at")
    val pushedAt: String,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("size")
    val size: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("allow_forking")
    val allowForking: Boolean,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("watchers")
    val watchers: Int,
    @SerializedName("default_branch")
    val defaultBranch: String,
)