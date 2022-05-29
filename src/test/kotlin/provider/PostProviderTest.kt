package provider

import api.PostApiService
import dto.PostDTO
import dto.UserDTO
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import model.Post
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
internal class `Post Provider Test` {

    @MockK
    private val postApiService: PostApiService = mockkClass(PostApiService::class)

    @InjectMockKs(injectImmutable = true)
    private val userProvider: UserProvider = mockkClass(UserProvider::class)


    private val postProvider = PostProvider(postApiService, userProvider)


    @Test
    fun `should get post when ok`() = runBlocking {
        //given
        val userDTO = UserDTO(1, "Test name", "Test username", "test@test.test")
        val postId = 1L
        val post = Post(postId, 1, "Post title", "Post body")
        coEvery { (postApiService.getPost(postId)) } returns post
        coEvery { userProvider.getUser(1) } returns userDTO
        //when
        val actual = postProvider.getPost(postId)
        //then
        assertNotNull(actual)
        assertEquals(PostDTO(postId, userDTO, "Post title", "Post body"), actual)
    }

    @Test
    fun `should get all posts when ok`() = runBlocking {
        //given
        val posts = arrayListOf(
            Post(
                1,
                1,
                "Post 1 title",
                "Post 1 body"
            ), Post(
                2,
                2,
                "Post 2 title",
                "Post 2 body"
            ), Post(
                3,
                3,
                "Post 3 title",
                "Post 3 body"
            )
        )
        val userDTO = UserDTO(1, "Test user", "Test username", "test@test.test")
        coEvery { postApiService.getAllPosts() } returns posts
        coEvery { userProvider.getUser(any()) } returns userDTO
        val expected = posts.map { PostDTO(it.id, userDTO, it.title, it.body) }
        //when
        val actual = postProvider.getAllPosts()
        //then
        assertNotNull(actual)
        assertEquals(actual.size, 3)
        assertEquals(actual, expected)
    }
}