package io.mellouk.main

import io.mellouk.common_android.base.BaseViewModel
import io.mellouk.main.ViewState.USER_PROFILE
import io.mellouk.main.di.MainScope
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor() : BaseViewModel<ViewState, Command>() {
    override fun getInitialState() = USER_PROFILE
    override fun onCommand(cmd: Command) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}