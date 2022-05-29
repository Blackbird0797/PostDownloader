package config

import service.FileReader

abstract class Configuration(protected val fileReader: FileReader, protected var configFileName: String?) {


    @kotlin.jvm.Throws(ConfigurationNotValidException::class)
    abstract suspend fun readConfiguration(): Configuration
}