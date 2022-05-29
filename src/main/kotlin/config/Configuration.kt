package config

import service.FileReader

abstract class Configuration(protected val fileReader: FileReader) {
    protected companion object {
        var DEFAULT_CONFIG_FILE_NAME: String? = "config.json"
    }

    @kotlin.jvm.Throws(ConfigurationNotValidException::class)
    abstract suspend fun readConfiguration(): Configuration
}