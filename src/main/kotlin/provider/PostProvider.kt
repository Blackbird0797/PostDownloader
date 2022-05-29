package provider

import api.PostApiService
import dto.PostDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostProvider(
    private val postApiService: PostApiService,
    private val userProvider: UserProvider,
    private val commentProvider: CommentProvider
) {
    private val coroutineContext = Dispatchers.IO

    suspend fun getPost(id: Long): PostDTO? {
        return withContext(coroutineContext) {
            postApiService.getPost(id)?.let {
                PostDTO(it.id, userProvider.getUser(it.userId), it.title, it.body, commentProvider.getCommentsByPostId(it.id))
            }
        }
    }

    suspend fun getAllPosts(): List<PostDTO>? {
        return withContext(coroutineContext) {
            postApiService.getAllPosts()
                ?.map { PostDTO(it.id, userProvider.getUser(it.userId), it.title, it.body, commentProvider.getCommentsByPostId(it.id)) }
        }
    }

}

