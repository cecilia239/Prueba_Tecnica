package com.example.pruebatecnica.data.Repositories.Post

import com.example.pruebatecnica.features.post.data.models.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    suspend fun getPost(): Response<List<PostModel>>
}