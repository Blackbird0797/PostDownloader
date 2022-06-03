package provider

import api.CommentApiService
import api.PostApiService
import api.UserApiService
import dto.CommentDTO
import dto.PostDTO
import dto.UserDTO
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import model.User
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class UserProviderTest {

    @MockK
    private lateinit var userApiService: UserApiService

    @MockK
    private lateinit var postProvider: PostProvider

    @InjectMockKs(injectImmutable = true)
    private lateinit var userProvider: UserProvider

    @Test
    fun shouldGetAllUsers() = runBlocking {
        //given
        val posts = arrayListOf(
            PostDTO(
                1, 1, "Post title", "Post body",
                arrayListOf(CommentDTO(1, 1, "name", "email", "body"))
            )
        )
        val users = arrayListOf(
            User(1, "name", "username", "email"), User(2, "name", "username", "email")
        )
        coEvery { postProvider.getPostsByUserId(any()) }.returns(posts)
        coEvery { userApiService.getAllUsers() }.returns(users)
        val expected = arrayListOf(
            UserDTO(1, "name", "username", "email", posts), UserDTO(2, "name", "username", "email", posts)
        )
        //when
        val actual = userProvider.getAllUsers()
        //then
        assertEquals(expected, actual)
    }

    @Test
    fun shouldGetUser() {
        //given
        //when
        //then
    }
}