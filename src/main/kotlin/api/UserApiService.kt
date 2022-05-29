package api

import model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("/users")
    suspend fun getAllUsers(): List<User>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Long): User
}
