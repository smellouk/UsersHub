package io.mellouk.user_profile

import io.mellouk.common_android.Commandable
import io.mellouk.common_android.base.BaseViewModel
import io.mellouk.common_android.exhaustive
import io.mellouk.user_profile.Command.GetUserProfile
import io.mellouk.user_profile.ViewState.Initial
import io.mellouk.user_profile.ViewState.Loading
import io.mellouk.user_profile.di.UserProfileScope
import io.mellouk.user_profile.domain.GetUserProfileParams
import io.mellouk.user_profile.domain.GetUserProfileUseCase
import io.mellouk.user_profile.domain.ViewStateMapper
import javax.inject.Inject

@UserProfileScope
class UserProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val stateMapper: ViewStateMapper
) : BaseViewModel<ViewState>(),
    Commandable<Command> {
    override fun getInitialState() = Initial

    override fun onCommand(cmd: Command) {
        liveData.value = commandHandler(cmd)
    }

    private fun commandHandler(cmd: Command) = when (cmd) {
        is GetUserProfile -> getUserProfile(cmd)
    }.exhaustive

    private fun getUserProfile(cmd: GetUserProfile): Loading {
        requestUserProfile(cmd.params)
        return Loading
    }

    private fun requestUserProfile(params: GetUserProfileParams) {
        addObservable(
            getUserProfileUseCase.buildObservable(params), { dataState ->
                liveData.value = stateMapper.map(dataState)
            }, { throwable ->
                liveData.value = stateMapper.map(throwable)
            }
        )
    }
}