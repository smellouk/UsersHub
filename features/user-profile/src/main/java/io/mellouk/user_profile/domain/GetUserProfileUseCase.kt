package io.mellouk.user_profile.domain

import io.mellouk.common_android.domain.BaseUseCase
import io.mellouk.common_android.domain.mapper.UserMapper
import io.mellouk.repositoy.remote.network.repositoty.UsersRepository
import io.mellouk.user_profile.di.UserProfileScope
import io.reactivex.Observable
import javax.inject.Inject

@UserProfileScope
class GetUserProfileUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val userMapper: UserMapper
) : BaseUseCase<GetUserProfileParams, UserDataState> {
    override fun buildObservable(params: GetUserProfileParams): Observable<UserDataState> =
        usersRepository.getUserProfile(params.username).toObservable().map { dto ->
            SuccessfulUserDataState(
                userMapper.map(
                    dto
                )
            )
        }
}