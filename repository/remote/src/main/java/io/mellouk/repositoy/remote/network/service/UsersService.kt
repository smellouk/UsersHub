package io.mellouk.repositoy.remote.network.service

import io.mellouk.repositoy.remote.dto.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {
    @GET("/users")
    fun getUsers(
        @Query("since")
        page: Int,
        @Query("per_page")
        pageSize: Int
    ): Single<List<UserDto>>
}