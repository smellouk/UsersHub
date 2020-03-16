package io.mellouk.common_android.domain.usecase

import io.mellouk.common_android.domain.BaseDataState
import io.mellouk.common_android.domain.model.User

sealed class UsersDataState : BaseDataState

class SuccessfulUsersDataState(val usersList: List<User>) : UsersDataState()