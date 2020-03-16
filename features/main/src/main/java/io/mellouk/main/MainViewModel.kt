package io.mellouk.main

import io.mellouk.common_android.Commandable
import io.mellouk.common_android.base.BaseViewModel
import io.mellouk.main.ViewState.UsersList
import io.mellouk.main.di.MainScope
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(
    private val viewStateMapper: ViewStateMapper
) : BaseViewModel<ViewState>(), Commandable<Command> {
    override fun getInitialState() = UsersList

    override fun onCommand(cmd: Command) {
        liveData.value = viewStateMapper.map(cmd)
    }
}