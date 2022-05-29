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
    private lateinit var postApiService: PostApiService
    private lateinit var userApiService: UserApiService


    fun getPostApiService(): PostApiService {
        synchronized(config) {
            if (!::postApiService.isInitialized) {
                val client = Retrofit.Builder()
                    .baseUrl(config.apiUri!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                postApiService = client.create(PostApiService::class.java)
            }
        }
        return postApiService
    }

    fun getUserApiService(): UserApiService {
        synchronized(config) {
            if (!::userApiService.isInitialized) {
                val client = Retrofit.Builder()
                    .baseUrl(config.apiUri!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                userApiService = client.create(UserApiService::class.java)
            }
        }
        return userApiService
    }
}