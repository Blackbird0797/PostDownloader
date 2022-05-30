package service

import com.google.gson.GsonBuilder
import dto.PostDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging

class PostSaver(private val fileSaverImpl: FileSaverImpl) : DTOSaver<PostDTO>() {

    private val logger = KotlinLogging.logger(this.javaClass.simpleName)
    private val gson = GsonBuilder().setPrettyPrinting().create()
    override suspend fun saveAll(dtoList: List<PostDTO>) = withContext(Dispatchers.Default) {
        dtoList.forEach {
            launch {
                logger.debug { "Saving file ${it.id} started.." }
                fileSaverImpl.saveFile(it.id.toString(), gson.toJson(it))
                logger.debug { "File ${it.id} saved!" }
            }
        }
    }

    override suspend fun save(dto: PostDTO) = withContext(Dispatchers.Default) {
        logger.debug { "Saving file ${dto.id} started.." }
        fileSaverImpl.saveFile(dto.id.toString(), gson.toJson(dto))
        logger.debug { "File ${dto.id} saved!" }
    }
}

