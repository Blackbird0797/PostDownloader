package service

import java.io.IOException

/**
 * Interface containing function for saving file
 */
interface Saver {
    @kotlin.jvm.Throws(FileSystemException::class, IOException::class)
    suspend fun saveFile(fileName: String, content: String?)
}
