package com.example.pruebatecnica.data

import com.example.pruebatecnica.data.Repositories.Album.AlbumRepository
import com.example.pruebatecnica.data.Repositories.Album.AlbumRepositoryImp
import com.example.pruebatecnica.data.Repositories.Album.AlbumService
import com.example.pruebatecnica.data.Repositories.Comments.CommentsRepository
import com.example.pruebatecnica.data.Repositories.Comments.CommentsRepositoryImp
import com.example.pruebatecnica.data.Repositories.Comments.CommentsService
import com.example.pruebatecnica.data.Repositories.Post.PostRepository
import com.example.pruebatecnica.data.Repositories.Post.PostRepositoryImp
import com.example.pruebatecnica.data.Repositories.Post.PostService
import com.example.pruebatecnica.data.Repositories.Repository
import com.example.pruebatecnica.features.album.ui.AlbumViewModel
import com.example.pruebatecnica.features.comments.ui.CommentsViewModel
import com.example.pruebatecnica.features.post.ui.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.module.Module
import org.koin.dsl.module

@KoinApiExtension
object DataModuleProvider {
    val module : Module = module {
        single { Repository.getRetrofit<PostService>("https://jsonplaceholder.typicode.com/") }
        single { Repository.getRetrofit<AlbumService>("https://jsonplaceholder.typicode.com/") }
        single { Repository.getRetrofit<CommentsService>("https://jsonplaceholder.typicode.com/") }
        single<PostRepository> { PostRepositoryImp(get()) }
        single<AlbumRepository> { AlbumRepositoryImp(get()) }
        single<CommentsRepository> { CommentsRepositoryImp(get()) }
        viewModel { PostViewModel(get()) }
        viewModel { AlbumViewModel(get()) }
        viewModel { CommentsViewModel(get()) }
        single { Repository.provideGson() }
    }
}