package api

import model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {

    @GET("/posts")
    suspend fun getAllPosts(): List<Post>

    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: Long): Post
}