package com.example.pruebatecnica.features.comments.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.commons.SingleLiveEvent
import com.example.pruebatecnica.data.Repositories.Comments.CommentsRepository
import com.example.pruebatecnica.features.comments.data.models.CommentsModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentsViewModel(private val commentsRepository: CommentsRepository) : ViewModel() {

    val commentsLiveData = SingleLiveEvent<List<CommentsModel?>>()

    fun getComments(id: Int) = viewModelScope.launch {
        commentsRepository.getComments(id)
            .catch {
                Log.e("error", it.message.toString())
            }
            .collect {
                it.let {
                    commentsLiveData.postValue(it)
                }
            }
    }
}