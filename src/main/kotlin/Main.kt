import api.ApiClient
import config.ApiConfiguration
import config.FileConfiguration
import kotlinx.coroutines.runBlocking
import provider.PostProvider
import provider.UserProvider
import service.FileReaderImpl
import service.FileSaverImpl
import service.PostSaver

fun main() = runBlocking {
    val fileReader = FileReaderImpl(System.getProperty("user.dir"))
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
    posts?.let { posts ->
        val fileSaver = FileSaverImpl(fileConfiguration.fileSavePath!!)
        val postSaver = PostSaver(fileSaver)
        postSaver.savePosts(posts)
    }
    println("Job complete!")
}
