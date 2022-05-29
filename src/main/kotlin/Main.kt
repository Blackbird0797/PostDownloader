import api.ApiClient
import config.ApiConfiguration
import config.FileConfiguration
import kotlinx.coroutines.runBlocking
import provider.CommentProvider
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
    val commentApiService = apiClient.getCommentApiService()
    val userProvider = UserProvider(userApiService)
    val commentProvider = CommentProvider(commentApiService)
    val postProvider = PostProvider(postApiService, userProvider, commentProvider)
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
