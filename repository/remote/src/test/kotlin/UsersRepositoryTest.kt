import com.google.gson.Gson
import io.mellouk.repositoy.remote.dto.UserDto
import io.mellouk.repositoy.remote.network.repositoty.UsersRepository
import io.mellouk.repositoy.remote.network.service.UsersService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {
    @RelaxedMockK
    lateinit var usersService: UsersService

    private lateinit var usersRepository: UsersRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        usersRepository = UsersRepository(usersService)
    }

    @Test
    fun getUsers_ShouldReturnListOfUsers() {
        every {
            usersService.getUsers(GIVEN_PAGE, GIVE_PAGE_SIZE)
        } returns Single.just(listOf(givenUser()))

        usersRepository.getUsers(GIVEN_PAGE, GIVE_PAGE_SIZE)
            .test()
            .apply {
                assertComplete()
                assertNoErrors()
                assertValueCount(1)
                assertValue { users ->
                    users.isNotEmpty() && users.first() == givenUser()
                }
            }
    }
}

//region Given
private const val GIVEN_PAGE = 1
private const val GIVE_PAGE_SIZE = 10
private val gson = Gson()
private val givenJson = """
        {
            "login": "mojombo",
            "id": 1,
            "node_id": "MDQ6VXNlcjE=",
            "avatar_url": "https://avatars0.githubusercontent.com/u/1?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/mojombo",
            "html_url": "https://github.com/mojombo",
            "followers_url": "https://api.github.com/users/mojombo/followers",
            "following_url": "https://api.github.com/users/mojombo/following{/other_user}",
            "gists_url": "https://api.github.com/users/mojombo/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/mojombo/subscriptions",
            "organizations_url": "https://api.github.com/users/mojombo/orgs",
            "repos_url": "https://api.github.com/users/mojombo/repos",
            "events_url": "https://api.github.com/users/mojombo/events{/privacy}",
            "received_events_url": "https://api.github.com/users/mojombo/received_events",
            "type": "User",
            "site_admin": false
          }
    """.trimIndent()

private fun givenUser() = gson.fromJson(givenJson, UserDto::class.java)
//endregion