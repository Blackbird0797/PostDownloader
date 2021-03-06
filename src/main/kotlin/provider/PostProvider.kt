package provider

import api.PostApiService
import dto.PostDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostProvider(
    private val postApiService: PostApiService, private val commentProvider: CommentProvider
) {
    private val coroutineContext = Dispatchers.IO

    suspend fun getPost(id: Long): PostDTO = withContext(coroutineContext) {
        postApiService.getPost(id).let {
            PostDTO(it.id, it.userId, it.title, it.body, commentProvider.getCommentsByPostId(it.id))
        }
    }

    suspend fun getAllPosts(): List<PostDTO> = withContext(coroutineContext) {
        postApiService.getAllPosts().map { PostDTO(it.id, it.userId, it.title, it.body, commentProvider.getCommentsByPostId(it.id)) }
    }

    suspend fun getPostsByUserId(userId: Long): List<PostDTO> = withContext(coroutineContext) {
        postApiService.getPostsByUserId(userId)
            .map { PostDTO(it.id, it.userId, it.title, it.body, commentProvider.getCommentsByPostId(it.id)) }
    }

}

