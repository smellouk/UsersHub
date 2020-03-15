package io.mellouk.main

import androidx.annotation.Keep
import io.mellouk.common_android.base.ViewState

@Keep
sealed class MainViewState : ViewState {
    object USER_LIST : MainViewState()
    object USER_PROFILE : MainViewState()
    object NO_INTERNET : MainViewState()
    object ERROR : MainViewState()
}