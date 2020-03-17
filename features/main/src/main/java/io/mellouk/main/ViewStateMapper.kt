package io.mellouk.main

import io.mellouk.common_android.exhaustive
import io.mellouk.main.Command.OpenUserList
import io.mellouk.main.Command.OpenUserProfile
import io.mellouk.main.ViewState.UserProfile
import io.mellouk.main.ViewState.UsersList
import io.mellouk.main.di.MainScope
import javax.inject.Inject

@MainScope
class ViewStateMapper @Inject constructor() {
    fun map(cmd: Command) = when (cmd) {
        OpenUserList -> UsersList
        is OpenUserProfile -> UserProfile(cmd.username)
    }.exhaustive
}