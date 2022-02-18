package com.example.pruebatecnica.data.Repositories.Post

import com.example.pruebatecnica.features.post.data.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost() : Flow<List<PostModel?>>
}