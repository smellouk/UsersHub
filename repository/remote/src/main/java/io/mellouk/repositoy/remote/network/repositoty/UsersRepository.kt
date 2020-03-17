package io.mellouk.repositoy.remote.network.repositoty

import io.mellouk.repositoy.remote.network.service.UsersService

class UsersRepository(private val usersService: UsersService) {
    fun getUsers(page: Int, pageSize: Int) = usersService.getUsers(page, pageSize)

    fun getUserProfile(username: String) = usersService.getUserProfile(username)
}