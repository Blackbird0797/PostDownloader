import api.ApiClient
import config.ApiConfiguration
import config.FileConfiguration
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import provider.CommentProvider
import provider.PostProvider
import provider.UserProvider
import service.FileReaderImpl
import service.FileSaverImpl
import service.UserSaver
import java.nio.file.Path

class Main {
    private val logger = KotlinLogging.logger(this.javaClass.simpleName)

    fun main() = runBlocking {
        val configPath = System.getProperty("configPath", "config.json").replace("?", "")
        val (configFileFolder, configFileName) = Path.of(configPath).let {
            if (it.isAbsolute) Pair(it.parent.toString(), it.fileName.toString()) else Pair(System.getProperty("user.dir"), it.toString())
        }
        val fileReader = FileReaderImpl(configFileFolder)
        val apiConfiguration = ApiConfiguration(fileReader, configFileName).readConfiguration()
        val fileConfiguration = FileConfiguration(fileReader, configFileName).readConfiguration()
        logger.debug { "Configurations read." }
        val apiClient = ApiClient(apiConfiguration)
        val postApiService = apiClient.getPostApiService()
        val userApiService = apiClient.getUserApiService()
        val commentApiService = apiClient.getCommentApiService()
        val commentProvider = CommentProvider(commentApiService)
        val postProvider = PostProvider(postApiService, commentProvider)
        val userProvider = UserProvider(userApiService, postProvider)
        logger.debug { "Getting posts from API..." }
        val allUsers = userProvider.getAllUsers()
//        val posts = postProvider.getAllPosts()
        logger.debug { "Posts retrieved!" }
        val fileSaver = FileSaverImpl(fileConfiguration.fileSavePath!!)
//        val postSaver = PostSaver(fileSaver)
//        postSaver.saveAll(posts)
        val userSaver = UserSaver(fileSaver)
        userSaver.saveAll(allUsers)
        logger.debug { "Job complete!" }
    }
}

fun main() {
    Main().main()
}

