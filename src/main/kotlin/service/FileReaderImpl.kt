package service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Path

class FileReaderImpl(folderPath: String) : FileReader(folderPath, fileFormat = "") {
    override suspend fun readFile(fileName: String): String {
        val filePath = Path.of(
            folderPath, "$fileName${
                if (fileFormat.isNotEmpty()) ".$fileFormat" else ""
            }"
        ).toString()
        val bufferedReader = File(filePath).bufferedReader()
        return withContext(Dispatchers.IO) {
            bufferedReader.use {
                it.readText()
            }
        }
    }
}