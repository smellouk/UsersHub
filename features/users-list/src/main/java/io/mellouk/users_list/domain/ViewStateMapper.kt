package io.mellouk.users_list.domain

import io.mellouk.common_android.domain.usecase.SuccessfulUsersDataState
import io.mellouk.common_android.domain.usecase.UsersDataState
import io.mellouk.users_list.ViewState.Error
import io.mellouk.users_list.ViewState.UsersListReady
import io.mellouk.users_list.di.UsersListScope
import javax.inject.Inject

@UsersListScope
class ViewStateMapper @Inject constructor() {
    fun map(dataState: UsersDataState, isLoadMore: Boolean) = when (dataState) {
        is SuccessfulUsersDataState -> UsersListReady(dataState.usersList, isLoadMore)
    }

    fun map(throwable: Throwable?) = Error(throwable?.message)
}