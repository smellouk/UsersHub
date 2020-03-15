package io.mellouk.main

import androidx.annotation.Keep
import io.mellouk.common_android.base.BaseViewState

@Keep
sealed class ViewState : BaseViewState {
    object USER_LIST : ViewState()
    object USER_PROFILE : ViewState()
}