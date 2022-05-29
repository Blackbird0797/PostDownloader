package provider

import api.CommentApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentProvider(private val commentApiService: CommentApiService) {

    suspend fun getCommentByPostId(postId: Long) = withContext(Dispatchers.IO) {
        commentApiService.getCommentByPostId(postId).map { it.toDTO() }
    }
}
