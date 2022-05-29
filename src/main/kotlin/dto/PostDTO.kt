package dto

class PostDTO(val id: Long, val user: UserDTO, val title: String, val body: String, val comments: List<CommentDTO>) {
    override fun toString(): String {
        return "[$id] User [${user.username}] posted \"${title}\":\n$body\n"
    }

    override fun hashCode(): Int {
        return 31 * id.hashCode() + user.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PostDTO) return false
        return (id == other.id) && (user == other.user) && (title === other.title) && (body == other.body) && (comments == other.comments)
    }
}