package com.example.pruebatecnica.features.post.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.commons.SingleLiveEvent
import com.example.pruebatecnica.data.Repositories.Post.PostRepository
import com.example.pruebatecnica.features.post.data.models.PostModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository): ViewModel() {

    val postLiveData = SingleLiveEvent<List<PostModel?>>()
    val goToAlbum = SingleLiveEvent<Int>()
    val goToComments = SingleLiveEvent<Int>()

    fun getPost() = viewModelScope.launch {
        postRepository.getPost()
            .catch {
                Log.e("error", it.message.toString())
            }
            .collect {
                it?.let {
                    postLiveData.postValue(it)
                }
            }
    }

    fun goToAlbum(id: Int) {
        goToAlbum.postValue(id)
    }

    fun goToComments(id: Int) {
        goToComments.postValue(id)
    }
}