package model

import dto.UserDTO

data class User(val id: Long, val name: String, val username: String, val email: String) {
    fun toDTO() = UserDTO(id, name, username, email)

    companion object {
        fun fromDTO(userDTO: UserDTO) = User(userDTO.id, userDTO.name, userDTO.username, userDTO.email)
    }
}

