package config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import service.FileReader
import java.io.FileNotFoundException
import java.io.IOException

class ApiConfiguration(fileReader: FileReader) : Configuration(fileReader) {
    @Expose
    @SerializedName("ApiUri")
    var apiUri: String? = null

    private val gson: Gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @kotlin.jvm.Throws(IOException::class, FileNotFoundException::class, JsonSyntaxException::class)
    override suspend fun readConfiguration(): ApiConfiguration {
        DEFAULT_CONFIG_FILE_NAME?.let {
            val fileContent = fileReader.readFile(it)
            val config = gson.fromJson(fileContent, this::class.java)
            if (config.apiUri.isNullOrBlank()) {
                throw ConfigurationNotValidException("ApiUri is not configured!. Check $it configuration file!")
            }
            return config
        }
        throw ConfigurationFileNameNotSpecifiedException("Configuration file is not specified!")
    }
}