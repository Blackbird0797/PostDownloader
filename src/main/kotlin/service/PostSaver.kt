package service

import com.google.gson.GsonBuilder
import dto.PostDTO
import kotlinx.coroutines.*

class PostSaver(private val fileSaverImpl: FileSaverImpl) {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    suspend fun savePosts(posts: List<PostDTO>) = withContext(Dispatchers.Default) {
        val savePostJobs = arrayListOf<Job>()
        posts.forEach {
            savePostJobs.add(async(start = CoroutineStart.LAZY) {
                println("Saving file ${it.id} started..")
                fileSaverImpl.saveFile(it.id.toString(), gson.toJson(it))
                println("File ${it.id} saved!")
            })
        }
        savePostJobs.forEach { it.start() }
        savePostJobs.joinAll()
    }
}