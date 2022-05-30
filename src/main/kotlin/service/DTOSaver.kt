package service

abstract class DTOSaver<in T> {
    abstract suspend fun saveAll(dtoList: List<T>)
    abstract suspend fun save(dto: T)
}