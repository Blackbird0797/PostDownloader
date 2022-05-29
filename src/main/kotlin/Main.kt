import api.ApiClient
import com.google.gson.GsonBuilder
import config.ApiConfiguration
import config.FileConfiguration
import kotlinx.coroutines.*
import provider.PostProvider
import provider.UserProvider
import service.FileReaderImpl
import service.FileSaverImpl

fun main() {
    val fileReader = FileReaderImpl(System.getProperty("user.dir"))
    runBlocking {
        val apiConfiguration = ApiConfiguration(fileReader).readConfiguration()
        val fileConfiguration = FileConfiguration(fileReader).readConfiguration()
        println("Configurations read.")
        val apiClient = ApiClient(apiConfiguration)
        val postApiService = apiClient.getPostApiService()
        val userApiService = apiClient.getUserApiService()
        val userProvider = UserProvider(userApiService)
        val postProvider = PostProvider(postApiService, userProvider)
        println("Getting posts from API...")
        val posts = postProvider.getAllPosts()
        println("Posts retrieved!")
        val fileSaver = FileSaverImpl(fileConfiguration.fileSavePath!!)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val saveFileJobs = arrayListOf<Job>()

        posts?.forEach {
            saveFileJobs.add(async(start = CoroutineStart.LAZY) {
                println("Saving file ${it.id} started..")
                fileSaver.saveFile(it.id.toString(), gson.toJson(it))
                println("File ${it.id} saved!")
            })
        }
        saveFileJobs.forEach { it.start() }
        joinAll(*saveFileJobs.toTypedArray())
    }
}
