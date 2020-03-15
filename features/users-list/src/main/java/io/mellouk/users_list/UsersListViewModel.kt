package io.mellouk.users_list

import io.mellouk.common_android.base.BaseViewModel
import io.mellouk.users_list.ViewState.INITIAL
import io.mellouk.users_list.ViewState.LOADING
import io.mellouk.users_list.di.UsersListScope
import javax.inject.Inject

@UsersListScope
class UsersListViewModel @Inject constructor() : BaseViewModel<ViewState, Command>() {
    override fun getInitialState(): ViewState = INITIAL
    override fun onCommand(cmd: Command) {
        liveData.value = LOADING

    }
}