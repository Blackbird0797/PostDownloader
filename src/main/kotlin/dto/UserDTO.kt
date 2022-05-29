package dto

class UserDTO(val id: Long, val name: String, val username: String, val email: String) {

    override fun toString(): String {
        return "User [$id] {name= $name, username= $username, email= $email"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserDTO) return false
        return (id == other.id) && (name == other.name) && (username == other.username) && (email == other.email)
    }

    override fun hashCode(): Int {
        return 31 * id.hashCode() + name.hashCode()
    }
}