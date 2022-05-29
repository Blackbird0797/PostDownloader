package model

import dto.CommentDTO

data class Comment(val postId: Long, val id: Long, val name: String, val email: String, val body: String) {
    fun toDTO() = CommentDTO(postId, id, name, email, body)
}