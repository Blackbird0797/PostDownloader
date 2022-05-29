package provider

import api.UserApiService
import dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserProvider(private val userApiService: UserApiService) {
    suspend fun getUser(id: Long): UserDTO = withContext(Dispatchers.IO) {
        userApiService.getUser(id).toDTO()
    }
}