package io.mellouk.user_profile.domain

import io.mellouk.common_android.domain.BaseDataState
import io.mellouk.common_android.domain.model.User

sealed class UserDataState : BaseDataState

class SuccessfulUserDataState(val user: User) : UserDataState()