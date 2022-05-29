package provider

import api.CommentApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentProvider(private val commentApiService: CommentApiService) {

    suspend fun getCommentsByPostId(postId: Long) = withContext(Dispatchers.IO) {
        commentApiService.getCommentsByPostId(postId).map { it.toDTO() }
    }
}
