package model

import dto.UserDTO

data class User(val id: Long, val name: String, val username: String, val email: String) {
    fun toDTO() = UserDTO(id, name, username, email)
}

