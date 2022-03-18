package ru.nikitaartamonov.githubclient.domain.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("company")
    val company: String?,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("location")
    val location: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("hireable")
    val hireable: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("twitter_username")
    val twitter_username: String?,
    @SerializedName("public_repos")
    val public_repos: Int,
    @SerializedName("public_gists")
    val public_gists: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String,
) {
    companion object {
        fun generateOtherUserInfo(userEntity: UserEntity): String {
            val sb = StringBuilder("|")
            userEntity.apply {
                if (company != null) sb.append(company).append("|")
                if (blog != "") sb.append(blog).append("|")
                if (location != null) sb.append(location).append("|")
                if (email != null) sb.append(email).append("|")
                if (hireable != null) sb.append(hireable).append("|")
                if (bio != null) sb.append(bio).append("|")
                if (twitter_username != null) sb.append(twitter_username).append("|")
                sb.append(public_repos).append("|")
                sb.append(public_gists).append("|")
                sb.append(followers).append("|")
                sb.append(following).append("|")
                sb.append(created_at).append("|")
                sb.append(updated_at).append("|")
            }
            return sb.toString()
        }
    }
}