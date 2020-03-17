package io.mellouk.user_profile

import androidx.annotation.Keep
import io.mellouk.common_android.base.BaseViewState
import io.mellouk.common_android.domain.model.User

@Keep
sealed class ViewState : BaseViewState {
    object Initial : ViewState()
    class UserReady(val user: User) : ViewState()
    object Loading : ViewState()
    class Error(val message: String?) : ViewState()
}