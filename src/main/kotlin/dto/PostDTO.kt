package dto

class PostDTO(val id: Long, val userId: Long, val title: String, val body: String, val comments: List<CommentDTO>) {
    override fun toString(): String = "[$id] User [${userId}] posted \"${title}\":\n$body\n"

    override fun hashCode(): Int = 31 * id.hashCode() + userId.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PostDTO) return false
        return (id == other.id) && (userId == other.userId) && (title === other.title) && (body == other.body) && (comments == other.comments)
    }
}