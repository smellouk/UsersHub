package io.mellouk.users_list.domain

import io.mellouk.common_android.domain.model.User
import io.mellouk.common_android.utils.Defaults.DEFAULT_STRING
import io.mellouk.repositoy.remote.dto.UserDto
import io.mellouk.users_list.di.UsersListScope
import javax.inject.Inject

@UsersListScope
class UserMapper @Inject constructor() {
    fun map(usersList: List<UserDto>) = usersList.map { dto ->
        map(dto)
    }

    private fun map(dto: UserDto) = User(
        name = dto.login ?: DEFAULT_STRING,
        avatar = dto.avatarUrl ?: DEFAULT_STRING
    )
}