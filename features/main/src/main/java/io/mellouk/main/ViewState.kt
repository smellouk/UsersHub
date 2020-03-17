package io.mellouk.main

import androidx.annotation.IdRes
import androidx.annotation.Keep
import io.mellouk.common_android.base.BaseViewState

@Keep
sealed class ViewState : BaseViewState {
    object UsersList : ViewState() {
        @IdRes
        val destination: Int = R.id.user_list_fragment
    }

    class UserProfile(val username: String, @IdRes val destination: Int = R.id.user_profile_fragment) :
        ViewState()
}