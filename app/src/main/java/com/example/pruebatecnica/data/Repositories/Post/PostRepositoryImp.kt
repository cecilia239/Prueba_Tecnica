package com.example.pruebatecnica.data.Repositories.Post

import com.example.pruebatecnica.features.post.data.models.PostModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImp(private val api: PostService) : PostRepository {

    override suspend fun getPost(): Flow<List<PostModel?>> = flow {
        val response = api.getPost()
        if (!response.body().isNullOrEmpty())
            emit(response.body()!!)
    }
}