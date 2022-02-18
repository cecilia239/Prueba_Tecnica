package com.example.pruebatecnica.data.Repositories.Album

import com.example.pruebatecnica.features.album.data.models.AlbumModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumRepositoryImp(private val api: AlbumService) : AlbumRepository {

    override suspend fun getAlbum(id: Int): Flow<List<AlbumModel?>> = flow {
        val response = api.getPhotos(id)
        if (!response.body().isNullOrEmpty())
            emit(response.body()!!)
    }
}