package io.mellouk.users_list

import androidx.annotation.Keep
import io.mellouk.common_android.base.BaseViewState

@Keep
sealed class ViewState : BaseViewState {
    object INITIAL : ViewState()
    object LOADING : ViewState()
    object NO_INTERNET : ViewState()
    object ERROR : ViewState()
}