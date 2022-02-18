package com.example.pruebatecnica.features.album.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.commons.SingleLiveEvent
import com.example.pruebatecnica.data.Repositories.Album.AlbumRepository
import com.example.pruebatecnica.features.album.data.models.AlbumModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    val albumLiveData = SingleLiveEvent<List<AlbumModel?>>()

    fun getPhotos(id: Int) = viewModelScope.launch {
        albumRepository.getAlbum(id)
            .catch {
                Log.e("error", it.message.toString())
            }
            .collect {
                it.let {
                    albumLiveData.postValue(it)
                }
            }
    }
}
