package com.example.pruebatecnica.features.album.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pruebatecnica.databinding.ItemAlbumLayoutBinding
import com.example.pruebatecnica.features.album.data.models.AlbumModel

class AlbumAdapter(private val albumList: List<AlbumModel?>):
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun getItemId(p0: Int): Long {
        return albumList[p0]?.id!!.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemAlbumLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(
        albumList[position]!!
    )

    class ViewHolder(private val itemBinding: ItemAlbumLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(album: AlbumModel) {
            itemBinding.imageViewPhoto.load(album.thumbnailUrl)
        }
    }
}