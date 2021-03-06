package api

import model.Comment
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApiService {
    @GET("/comments")
    suspend fun getCommentsByPostId(@Query("postId") postId: Long): List<Comment>
}
