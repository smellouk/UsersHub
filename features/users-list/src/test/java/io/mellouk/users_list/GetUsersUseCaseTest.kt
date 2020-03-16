package io.mellouk.users_list

import io.mellouk.common_android.domain.model.User
import io.mellouk.common_android.domain.usecase.GetUsersListParams
import io.mellouk.common_android.domain.usecase.SuccessfulUsersDataState
import io.mellouk.repositoy.remote.dto.UserDto
import io.mellouk.repositoy.remote.network.repositoty.UsersRepository
import io.mellouk.users_list.domain.GetUsersUseCase
import io.mellouk.users_list.domain.UserMapper
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import org.junit.Test

class GetUsersUseCaseTest : BaseTest() {
    @RelaxedMockK
    lateinit var usersRepository: UsersRepository

    @RelaxedMockK
    lateinit var userMapper: UserMapper

    @InjectMockKs
    lateinit var getUsersUseCase: GetUsersUseCase

    @Test
    fun buildObservable_ShouldBuildUsersDataState() {
        every {
            usersRepository.getUsers(PAGE, PAGE_SIZE)
        } returns Single.just(givenListDtos)

        every {
            userMapper.map(givenListDtos)
        } returns giveListUsers

        val observable = getUsersUseCase.buildObservable(givenGetUsersListParams)
        observable.test().apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(1)
            assertValue { state ->
                state is SuccessfulUsersDataState
                        && state.usersList.isNotEmpty()
                        && state.usersList.contains(givenUser)
            }
        }
    }
}

private const val PAGE = 1
private const val PAGE_SIZE = 10

private val givenGetUsersListParams = GetUsersListParams(
    PAGE,
    PAGE_SIZE
)

private val givenDto = UserDto()
private val givenListDtos = listOf(givenDto)

private val givenUser = User("", "")
private val giveListUsers = listOf(givenUser)