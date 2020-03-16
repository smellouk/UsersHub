package io.mellouk.users_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.mellouk.common_android.base.BaseFragment
import io.mellouk.common_android.domain.model.User
import io.mellouk.common_android.domain.usecase.GetUsersListParams
import io.mellouk.common_android.exhaustive
import io.mellouk.common_android.hide
import io.mellouk.common_android.show
import io.mellouk.common_android.utils.EndlessRecyclerViewScrollListener
import io.mellouk.common_android.utils.MainNavigator
import io.mellouk.common_android.utils.UsersIdlingResource
import io.mellouk.users_list.Command.GetUsers
import io.mellouk.users_list.Command.LoadMoreUsers
import io.mellouk.users_list.ViewState.*
import io.mellouk.users_list.di.UsersListComponentProvider
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.error_view.view.*
import kotlinx.android.synthetic.main.fragment_users_list.*
import javax.inject.Inject


class UserListFragment : BaseFragment<UsersListComponentProvider, ViewState, UsersListViewModel>(
    R.layout.fragment_users_list
) {
    @Inject
    lateinit var idlingResource: UsersIdlingResource

    private val onClick: (User) -> Unit = { user ->
        val mainNavigator = activity as? MainNavigator ?: throw IllegalArgumentException(
            "Activity[${activity?.javaClass?.name}] is not implementing ${MainNavigator::class.java.name}"
        )

        mainNavigator.navigateToUserProfile(user.name)
    }

    private val usersAdapter = UsersAdapter(onClick)

    override fun getViewModelClass(): Class<out UsersListViewModel> = UsersListViewModel::class.java

    override fun inject() {
        componentProvider.getUsersListComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvUsers.adapter = usersAdapter
        val linearLayoutManager = LinearLayoutManager(activity)
        rvUsers.layoutManager = linearLayoutManager
        rvUsers.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.onCommand(
                    LoadMoreUsers(
                        GetUsersListParams(
                            page,
                            PAGE_SIZE
                        )
                    )
                )
            }
        })

        errorView.btnRetry.setOnClickListener {
            loadUsersList()
        }

        swipeToRefresh.setOnRefreshListener {
            loadUsersList()
        }
    }

    override fun renderViewState(state: ViewState) {
        when (state) {
            Initial -> loadUsersList()
            is Error -> renderErrorView(state)
            NoInternet -> renderDefaultViewState()
            Loading -> renderLoading()
            is UsersListReady -> renderUserList(state)
        }.exhaustive
    }

    private fun renderErrorView(state: Error) {
        swipeToRefresh.isRefreshing = false
        errorView.tvError.text = state.message
        userViews.hide()
        errorView.show()
    }

    private fun renderLoading() {
        swipeToRefresh.isRefreshing = true
    }

    private fun loadUsersList() {
        errorView.hide()
        userViews.show()
        viewModel.onCommand(
            GetUsers(
                GetUsersListParams(
                    INITIAL_PAGE,
                    PAGE_SIZE
                )
            )
        )
        idlingResource.setIdleState(false)
    }

    private fun renderUserList(state: UsersListReady) {
        swipeToRefresh.isRefreshing = false
        with(usersAdapter) {
            if (state.isLoadMore) {
                addUsers(state.usersList)
            } else {
                setUsers(state.usersList)
            }
        }
        idlingResource.setIdleState(true)
    }
}

private const val INITIAL_PAGE = 1
private const val PAGE_SIZE = 20