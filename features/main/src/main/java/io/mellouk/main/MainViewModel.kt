package io.mellouk.main

import io.mellouk.common_android.base.BaseViewModel
import io.mellouk.main.MainViewState.USER_PROFILE
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel<MainViewState>() {
    override fun getInitialState() = USER_PROFILE
}