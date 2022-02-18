package com.example.pruebatecnica.data.Repositories.Comments

import com.example.pruebatecnica.features.comments.data.models.CommentsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommentsRepositoryImp(private val api:CommentsService): CommentsRepository {

    override suspend fun getComments(id: Int): Flow<List<CommentsModel?>> = flow {
        val response = api.getComments(id)
        if (!response.body().isNullOrEmpty())
            emit(response.body()!!)
    }
}