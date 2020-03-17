package io.mellouk.user_profile.domain

import io.mellouk.common_android.exhaustive
import io.mellouk.user_profile.ViewState.Error
import io.mellouk.user_profile.ViewState.UserReady
import io.mellouk.user_profile.di.UserProfileScope
import javax.inject.Inject

@UserProfileScope
class ViewStateMapper @Inject constructor() {
    fun map(dataState: UserDataState) = when (dataState) {
        is SuccessfulUserDataState -> UserReady(dataState.user)
    }.exhaustive

    fun map(throwable: Throwable?) = Error(throwable?.message)
}