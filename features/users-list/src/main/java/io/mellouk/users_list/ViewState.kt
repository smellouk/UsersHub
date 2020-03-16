package io.mellouk.users_list

import androidx.annotation.Keep
import io.mellouk.common_android.base.BaseViewState
import io.mellouk.common_android.domain.model.User

@Keep
sealed class ViewState : BaseViewState {
    object Initial : ViewState()
    object Loading : ViewState()
    object NoInternet : ViewState()
    class Error(val message: String?) : ViewState()
    class UsersListReady(
        val usersList: List<User>,
        val isLoadMore: Boolean
    ) : ViewState()
}