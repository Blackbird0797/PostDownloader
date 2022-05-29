package model

import dto.PostDTO


data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
) {

    companion object {
        fun fromDTO(postDTO: PostDTO) = Post(postDTO.id, postDTO.user.id, postDTO.title, postDTO.body)
    }
}

