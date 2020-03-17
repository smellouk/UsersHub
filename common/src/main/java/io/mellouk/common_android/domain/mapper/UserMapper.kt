package io.mellouk.common_android.domain.mapper

import io.mellouk.common_android.domain.model.User
import io.mellouk.common_android.utils.Defaults.DEFAULT_STRING
import io.mellouk.repositoy.remote.dto.UserDto

class UserMapper {
    fun map(usersList: List<UserDto>) = usersList.map { dto ->
        map(dto)
    }

    fun map(dto: UserDto) = User(
        username = dto.login ?: DEFAULT_STRING,
        avatar = dto.avatarUrl ?: DEFAULT_STRING,
        name = dto.name ?: DEFAULT_STRING,
        bio = dto.bio ?: DEFAULT_STRING,
        blog = dto.blog ?: DEFAULT_STRING,
        followers = dto.followers ?: DEFAULT_STRING,
        following = dto.following ?: DEFAULT_STRING
    )
}