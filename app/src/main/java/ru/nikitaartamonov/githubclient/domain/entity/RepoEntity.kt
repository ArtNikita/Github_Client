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
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("pushed_at")
    val pushed_at: String,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("size")
    val size: Int,
    @SerializedName("watchers_count")
    val watchers_count: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forks_count: Int,
    @SerializedName("license")
    val license: String?,
    @SerializedName("allow_forking")
    val allow_forking: Boolean,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("watchers")
    val watchers: Int,
    @SerializedName("default_branch")
    val default_branch: String,
)