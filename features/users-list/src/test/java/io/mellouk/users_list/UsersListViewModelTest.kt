package io.mellouk.users_list

import io.mellouk.common_android.domain.usecase.GetUsersListParams
import io.mellouk.common_android.domain.usecase.SuccessfulUsersDataState
import io.mellouk.users_list.Command.GetUsers
import io.mellouk.users_list.Command.LoadMoreUsers
import io.mellouk.users_list.domain.GetUsersUseCase
import io.mellouk.users_list.domain.ViewStateMapper
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Test

class UsersListViewModelTest : BaseTest() {
    @RelaxedMockK
    lateinit var getUsersUseCase: GetUsersUseCase

    @RelaxedMockK
    lateinit var stateMapper: ViewStateMapper

    @InjectMockKs
    lateinit var viewModel: UsersListViewModel

    @Test
    fun onGetUserCommand_ShouldGetUsers() {
        every {
            getUsersUseCase.buildObservable(givenGetUsersCommandParams)
        } returns Observable.just(SuccessfulUsersDataState(listOf()))

        viewModel.onCommand(givenGetUserCommand)

        verify {
            getUsersUseCase.buildObservable(givenGetUsersCommandParams)
            stateMapper.map(any(), false)
        }
    }

    @Test
    fun onLoadMoreUsersCommand_ShouldLoadUsers() {
        every {
            getUsersUseCase.buildObservable(givenLoadMoreUsersCommandParams)
        } returns Observable.just(SuccessfulUsersDataState(listOf()))

        viewModel.onCommand(givenLoadMoreUsersCommand)

        verify {
            getUsersUseCase.buildObservable(givenLoadMoreUsersCommandParams)
            stateMapper.map(any(), true)
        }
    }
}

private const val PAGE_1 = 1
private const val PAGE_2 = 2
private const val PAGE_SIZE = 10
private val givenGetUsersCommandParams = GetUsersListParams(
    PAGE_1,
    PAGE_SIZE
)

private val givenLoadMoreUsersCommandParams = GetUsersListParams(
    PAGE_2,
    PAGE_SIZE
)

private val givenGetUserCommand = GetUsers(
    givenGetUsersCommandParams
)

private val givenLoadMoreUsersCommand = LoadMoreUsers(
    givenLoadMoreUsersCommandParams
)