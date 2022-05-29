package service

abstract class FileReader(folderPath: String, fileFormat: String) : FileAccessor(folderPath, fileFormat), Reader

