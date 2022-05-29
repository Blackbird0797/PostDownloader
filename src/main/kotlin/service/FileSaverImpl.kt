package service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.nio.file.Path

open class FileSaverImpl(folderPath: String) : FileSaver(folderPath, fileFormat = "json"), Saver {
    override suspend fun saveFile(fileName: String, content: String?) {
        withContext(Dispatchers.IO) {
            val filePath = Path.of(folderPath, "$fileName.$fileFormat").toString()
            val dir = File(folderPath)
            if (dir.exists() || dir.mkdir()) {
                FileWriter(filePath).use { out ->
                    content?.let {
                        out.write(content)
                    }
                }
            }
        }
    }
}