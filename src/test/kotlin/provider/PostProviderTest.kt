package provider

import api.PostApiService
import dto.CommentDTO
import dto.PostDTO
import dto.UserDTO
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import model.Post
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
internal class PostProviderTest {

    @MockK
    private lateinit var postApiService: PostApiService

    @MockK
    private lateinit var commentProvider: CommentProvider

    @InjectMockKs(injectImmutable = true)
    private lateinit var postProvider: PostProvider


    @Test
    fun `should get post when ok`() = runTest {
        //given
        val postId = 1L
        val userId = 1L
        val comments = arrayListOf(
            CommentDTO(1, 1, "Comment name", "Author email", "Comment body"),
            CommentDTO(1, 2, "Comment name", "Author email", "Comment body"),
            CommentDTO(1, 3, "Comment name", "Author email", "Comment body"),
            CommentDTO(1, 4, "Comment name", "Author email", "Comment body")
        )
        UserDTO(userId, "Test name", "Test username", "test@test.test", arrayListOf(PostDTO(postId, userId, "Title", "Body", comments)))
        coEvery { (postApiService.getPost(postId)) } returns Post(postId, 1, "Post title", "Post body")
        coEvery { commentProvider.getCommentsByPostId(postId) } returns comments
        //when
        val actual = postProvider.getPost(postId)
        //then
        assertNotNull(actual)
        assertEquals(PostDTO(postId, userId, "Post title", "Post body", comments), actual)
    }

    @Test
    fun `should get all posts when ok`() = runTest {
        //given
        val posts = arrayListOf(
            Post(1, 1, "Post 1 title", "Post 1 body"),
            Post(2, 2, "Post 2 title", "Post 2 body"),
            Post(3, 3, "Post 3 title", "Post 3 body")
        )
        val comments = arrayListOf(
            CommentDTO(1, 1, "Comment name", "Author email", "Comment body"),
            CommentDTO(1, 2, "Comment name", "Author email", "Comment body"),
            CommentDTO(1, 3, "Comment name", "Author email", "Comment body"),
            CommentDTO(1, 4, "Comment name", "Author email", "Comment body")
        )

        coEvery { postApiService.getAllPosts() } returns posts
        coEvery { commentProvider.getCommentsByPostId(any()) } returns comments
        val expected = posts.map { PostDTO(it.id, it.userId, it.title, it.body, comments) }
        //when
        val actual = postProvider.getAllPosts()
        //then
        assertNotNull(actual)
        assertEquals(actual.size, 3)
        assertEquals(actual, expected)
    }
}