package config

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import service.FileReader

class FileConfiguration(fileReader: FileReader) : Configuration(fileReader) {

    private val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Expose
    @SerializedName("FileSavePath")
    var fileSavePath: String? = null

    override suspend fun readConfiguration(): FileConfiguration {
        DEFAULT_CONFIG_FILE_NAME?.let {
            val fileContent = fileReader.readFile(it)
            val config = gson.fromJson(fileContent, this::class.java)
            if (config.fileSavePath.isNullOrBlank()) {
                throw ConfigurationNotValidException("FileSavePath is not specified!. Check $it configuration file!")
            }
            return config
        }
        throw ConfigurationFileNameNotSpecifiedException("Configuration file is not specified!")
    }
}