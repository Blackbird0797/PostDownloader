package service

abstract class FileSaver(folderPath: String, fileFormat: String) : FileAccessor(folderPath, fileFormat), Saver
