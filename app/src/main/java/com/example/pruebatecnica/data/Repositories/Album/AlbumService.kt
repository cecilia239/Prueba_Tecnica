package com.example.pruebatecnica.data.Repositories.Album

import com.example.pruebatecnica.features.album.data.models.AlbumModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("/posts/{idpost}/photos")
    suspend fun getPhotos(
        @Path("idpost") idpost: Int
    ): Response<List<AlbumModel>>
}