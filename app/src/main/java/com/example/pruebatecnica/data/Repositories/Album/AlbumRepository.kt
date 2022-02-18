package com.example.pruebatecnica.data.Repositories.Album


import com.example.pruebatecnica.features.album.data.models.AlbumModel
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbum(id: Int): Flow<List<AlbumModel?>>
}