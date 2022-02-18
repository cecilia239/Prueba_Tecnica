package com.example.pruebatecnica.data.Repositories.Comments

import com.example.pruebatecnica.features.album.data.models.AlbumModel
import com.example.pruebatecnica.features.comments.data.models.CommentsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsService {
    @GET("/posts/{idpost}/comments")
    suspend fun getComments(
        @Path("idpost") idpost: Int
    ): Response<List<CommentsModel>>
}