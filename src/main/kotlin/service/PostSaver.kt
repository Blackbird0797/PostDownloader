package service

import com.google.gson.GsonBuilder
import dto.PostDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging

class PostSaver(private val fileSaverImpl: FileSaverImpl) {

    private val logger = KotlinLogging.logger(this.javaClass.simpleName)
    private val gson = GsonBuilder().setPrettyPrinting().create()
    suspend fun savePosts(posts: List<PostDTO>) = withContext(Dispatchers.Default) {
        posts.map {
            launch {
                logger.debug { "Saving file ${it.id} started.." }
                fileSaverImpl.saveFile(it.id.toString(), gson.toJson(it))
                logger.debug { "File ${it.id} saved!" }
            }
        }
    }
}