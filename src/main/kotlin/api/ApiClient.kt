package api

import config.ApiConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Client accessing api services via Retrofit library
 * @constructor
 * @param config config passing configuration containing api base url
 *
 */
class ApiClient(private val config: ApiConfiguration) {
    private lateinit var postApiService: PostApiService
    private lateinit var userApiService: UserApiService

    fun getPostApiService(): PostApiService {
        if (!::postApiService.isInitialized) {
            val client = Retrofit.Builder()
                .baseUrl(config.apiUri!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            postApiService = client.create(PostApiService::class.java)
        }
        return postApiService
    }

    fun getUserApiService(): UserApiService {
        if (!::userApiService.isInitialized) {
            val client = Retrofit.Builder()
                .baseUrl(config.apiUri!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            userApiService = client.create(UserApiService::class.java)
        }
        return userApiService
    }
}