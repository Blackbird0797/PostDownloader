package api

import config.ApiConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Client accessing api services via Retrofit library.
 * Contains functions returning services operating on
 * @constructor
 * @param config ApiConfiguration object containing api client configuration
 */
class ApiClient(private val config: ApiConfiguration) {

    private val client = Retrofit.Builder()
        .baseUrl(config.apiUri!!)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postApiService: PostApiService by lazy {
        client.create(PostApiService::class.java)
    }

    val userApiService: UserApiService by lazy {
        client.create(UserApiService::class.java)
    }

    val commentApiService: CommentApiService by lazy {
        client.create(CommentApiService::class.java)
    }

}