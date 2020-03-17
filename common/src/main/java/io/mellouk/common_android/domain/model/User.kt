package io.mellouk.common_android.domain.model

data class User(
    val username: String,
    val avatar: String,
    val name: String,
    val bio: String,
    val blog: String,
    val followers: String,
    val following: String
) {
    fun summary() = "Bio: $bio\nBlog: $blog\nFollowers: $followers\nFollowing: $following"
}