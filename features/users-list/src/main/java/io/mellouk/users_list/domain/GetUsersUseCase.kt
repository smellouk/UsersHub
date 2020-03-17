package io.mellouk.users_list.domain

import io.mellouk.common_android.domain.BaseUseCase
import io.mellouk.common_android.domain.mapper.UserMapper
import io.mellouk.repositoy.remote.network.repositoty.UsersRepository
import io.mellouk.users_list.di.UsersListScope
import io.reactivex.Observable
import javax.inject.Inject

@UsersListScope
class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val userMapper: UserMapper
) : BaseUseCase<GetUsersListParams, UsersDataState> {
    override fun buildObservable(params: GetUsersListParams): Observable<UsersDataState> =
        usersRepository.getUsers(params.page, params.pageSize).toObservable().map { dtos ->
            SuccessfulUsersDataState(
                userMapper.map(
                    dtos
                )
            )
        }
}