package service

import com.google.gson.GsonBuilder
import dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging

class UserSaver(private val fileSaverImpl: FileSaverImpl) : DTOSaver<UserDTO>() {

    private val logger = KotlinLogging.logger(this.javaClass.simpleName)
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override suspend fun saveAll(dtoList: List<UserDTO>) = withContext(Dispatchers.Default) {
        dtoList.forEach {
            launch {
                logger.debug { "Saving user ${it.id} started.." }
                fileSaverImpl.saveFile(it.id.toString(), gson.toJson(it))
                logger.debug { "User ${it.id} saved!" }
            }
        }
    }

    override suspend fun save(dto: UserDTO) {
        TODO("Not yet implemented")
    }
}