package io.mellouk.users_list

import io.mellouk.common_android.base.BaseFragment
import io.mellouk.users_list.Command.GetUsers
import io.mellouk.users_list.di.UsersListComponentProvider


class UserListFragment : BaseFragment<UsersListComponentProvider,
        ViewState, Command, UsersListViewModel>(
    R.layout.fragment_users_list
) {
    override fun getViewModelClass(): Class<out UsersListViewModel> = UsersListViewModel::class.java

    override fun inject() {
        componentProvider.getUsersListComponent().inject(this)
    }

    override fun renderViewState(state: ViewState) {
        when (state) {
            ViewState.INITIAL -> loadUsersList()
            ViewState.ERROR -> renderDefaultViewState()
            ViewState.NO_INTERNET -> renderDefaultViewState()
        }
    }

    private fun loadUsersList() {
        viewModel.onCommand(GetUsers)
    }
}