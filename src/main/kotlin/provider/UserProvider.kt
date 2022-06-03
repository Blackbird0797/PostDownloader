package provider

import api.UserApiService
import dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class UserProvider(private val userApiService: UserApiService, private val postProvider: PostProvider) {

    suspend fun getAllUsers(): List<UserDTO> = withContext(Dispatchers.IO) {
        val deferredList = userApiService.getAllUsers().map {
            async {
                UserDTO(it.id, it.name, it.username, it.email, postProvider.getPostsByUserId(it.id))
            }
        }
        deferredList.awaitAll()
    }

    suspend fun getUser(id: Long): UserDTO = withContext(Dispatchers.IO) {
        userApiService.getUser(id).let { UserDTO(it.id, it.name, it.username, it.email, postProvider.getPostsByUserId(it.id)) }
    }

}