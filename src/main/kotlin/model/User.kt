package model

import dto.UserDTO

data class User(val id: Long, val name: String, val username: String, val email: String) {
    override fun toString(): String {
        return "User [$id] {name= $name, username= $username, email= $email"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return (id == other.id) && (name == other.name) && (username == other.username) && (email == other.email)
    }

    override fun hashCode(): Int {
        return 31 * id.hashCode() + name.hashCode()
    }

    fun toDTO() = UserDTO(id, name, username, email)

    companion object{
        fun fromDTO(userDTO: UserDTO) = User(userDTO.id, userDTO.name, userDTO.username, userDTO.email)
    }
}

