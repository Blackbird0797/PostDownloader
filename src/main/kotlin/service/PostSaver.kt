package service

import com.google.gson.GsonBuilder
import dto.PostDTO
import kotlinx.coroutines.*

class PostSaver(private val fileSaverImpl: FileSaverImpl, private val fileChunkSize: Int = 25) {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    suspend fun savePosts(posts: List<PostDTO>) = withContext(Dispatchers.Default) {
        val savePostJobs = posts.map {
            async(start = CoroutineStart.LAZY) {
                println("Saving file ${it.id} started..")
                fileSaverImpl.saveFile(it.id.toString(), gson.toJson(it))
                println("File ${it.id} saved!")
            }
        }.toList()
        savePostJobs.chunked(fileChunkSize).forEach {
            it.forEach { job ->
                job.start()
            }
            it.joinAll()
        }
    }
}