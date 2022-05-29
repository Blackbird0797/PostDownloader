package dto

class CommentDTO(val postId: Long, val id: Long, val name: String, val email: String, val body: String) {

    override fun toString(): String = "[$postId] $id $name/$email:\n$body"

    override fun hashCode(): Int = 31 * postId.hashCode() + id.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CommentDTO) return false
        return (postId == other.postId) && (id == other.id) && (name == other.name) && (email == other.email) && (body == other.body)
    }
}