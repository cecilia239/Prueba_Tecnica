package com.example.pruebatecnica.features.post.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.databinding.ItemPostLayoutBinding
import com.example.pruebatecnica.features.post.data.models.PostModel
import java.util.*
import kotlin.collections.ArrayList

class PostAdapter(private val postList: List<PostModel?>, private val viewModel: PostViewModel):
    RecyclerView.Adapter<PostAdapter.ViewHolder>(), Filterable {

    var postListAux: ArrayList<PostModel> = ArrayList()
    var postListFiltered: ArrayList<PostModel> = ArrayList()

    init {
        postListAux = postList as ArrayList<PostModel>
        postListFiltered = postListAux
        notifyDataSetChanged()
    }

    override fun getItemId(p0: Int): Long {
        return postListFiltered[p0]?.id!!.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount() = postListFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(
        postListFiltered[position]!!, viewModel
    )

    class ViewHolder(private val itemBinding: ItemPostLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(post: PostModel, viewModel: PostViewModel) {
            itemBinding.textViewTitle.text = post.title
            itemBinding.textViewDesc.text = post.body
            itemBinding.imageViewAlbum.setOnClickListener {
                viewModel.goToAlbum(post.id!!)
            }
            itemBinding.imageViewComment.setOnClickListener {
                viewModel.goToComments(post.id!!)
            }
        }
    }

    override fun getFilter(): Filter {
        return object  : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    postListFiltered = postListAux
                } else {
                    val resultList = ArrayList<PostModel>()
                    for (row in postListAux) {
                        if (row.body!!.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) ||
                            row.title!!.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    postListFiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = postListFiltered
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                postListFiltered = p1?.values as ArrayList<PostModel>
                notifyDataSetChanged()
            }
        }
    }
}