package com.example.pruebatecnica.data.Repositories.Comments

import com.example.pruebatecnica.features.comments.data.models.CommentsModel
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {
    suspend fun getComments(id: Int): Flow<List<CommentsModel?>>
}