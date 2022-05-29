package service

import java.io.IOException

/**
 * Interface containing function responsible for reading file
 */
interface Reader {
    @kotlin.jvm.Throws(FileSystemException::class, IOException::class)
    suspend fun readFile(fileName: String): String
}