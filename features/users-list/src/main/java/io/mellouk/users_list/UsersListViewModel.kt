package io.mellouk.users_list

import io.mellouk.common_android.Commandable
import io.mellouk.common_android.base.BaseViewModel
import io.mellouk.common_android.domain.usecase.GetUsersListParams
import io.mellouk.common_android.exhaustive
import io.mellouk.users_list.Command.GetUsers
import io.mellouk.users_list.Command.LoadMoreUsers
import io.mellouk.users_list.ViewState.Initial
import io.mellouk.users_list.ViewState.Loading
import io.mellouk.users_list.di.UsersListScope
import io.mellouk.users_list.domain.GetUsersUseCase
import io.mellouk.users_list.domain.ViewStateMapper
import javax.inject.Inject

@UsersListScope
class UsersListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val stateMapper: ViewStateMapper
) : BaseViewModel<ViewState>(), Commandable<Command> {
    override fun getInitialState(): ViewState = Initial

    override fun onCommand(cmd: Command) {
        liveData.value = commandHandler(cmd)
    }

    private fun commandHandler(cmd: Command) = when (cmd) {
        is GetUsers -> getUsers(cmd)
        is LoadMoreUsers -> loadMoreUsers(cmd)
    }.exhaustive

    private fun getUsers(cmd: GetUsers): Loading {
        requestUsers(cmd.params)
        return Loading
    }

    private fun loadMoreUsers(cmd: LoadMoreUsers): Loading {
        requestUsers(cmd.params, true)
        return Loading
    }

    private fun requestUsers(params: GetUsersListParams, isLoadMore: Boolean = false) {
        addObservable(
            getUsersUseCase.buildObservable(params), { dataState ->
                liveData.value = stateMapper.map(dataState, isLoadMore)
            }, { throwable ->
                liveData.value = stateMapper.map(throwable)
            }
        )
    }
}