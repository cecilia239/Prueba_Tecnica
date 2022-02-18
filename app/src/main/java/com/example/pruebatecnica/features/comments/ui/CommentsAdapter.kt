package com.example.pruebatecnica.features.comments.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.databinding.ItemLeftCommentLayoutBinding
import com.example.pruebatecnica.databinding.ItemRightCommentLayoutBinding
import com.example.pruebatecnica.features.comments.data.models.CommentsModel
import com.example.pruebatecnica.features.post.data.models.PostModel
import java.util.*
import kotlin.collections.ArrayList

class CommentsAdapter(
    private val commentList: List<CommentsModel?>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var commentListAux: ArrayList<CommentsModel> = ArrayList()
    var commentListFiltered: ArrayList<CommentsModel> = ArrayList()

    init {
        commentListAux = commentList as ArrayList<CommentsModel>
        commentListFiltered = commentListAux
        notifyDataSetChanged()
    }

    override fun getItemId(p0: Int): Long {
        return commentListFiltered[p0]?.id!!.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            TYPE_RIGHT -> {
                val viewBinding = ItemRightCommentLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemLeftCommentLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolderLeft(viewBinding)
            }
        }

    }

    override fun getItemCount() = commentListFiltered.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_RIGHT -> {
                val viewHolder = holder as ViewHolder
                viewHolder.bind(commentListFiltered[position]!!)
            }
            else -> {
                val viewHolder = holder as ViewHolderLeft
                viewHolder.bind(commentListFiltered[position]!!)
            }
        }
    }

    class ViewHolder(private val itemBinding: ItemRightCommentLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(comment: CommentsModel) {
            itemBinding.textViewBodyR.text = comment.body
            itemBinding.textViewEmailR.text = comment.email

        }
    }

    class ViewHolderLeft(private val itemBinding: ItemLeftCommentLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(comment: CommentsModel) {
            itemBinding.textViewBodyL.text = comment.body
            itemBinding.textViewEmailL.text = comment.email

        }
    }

    override fun getItemViewType(position: Int) = when {
        position % 2 == 0 -> TYPE_RIGHT
        else -> TYPE_LEFT
    }

    companion object {
        const val TYPE_RIGHT = 0
        const val TYPE_LEFT = 1
    }

    override fun getFilter(): Filter {
        return object  : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    commentListFiltered = commentListAux
                } else {
                    val resultList = ArrayList<CommentsModel>()
                    for (row in commentListAux) {
                        if (row.body!!.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) ||
                            row.email!!.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    commentListFiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = commentListFiltered
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                commentListFiltered = p1?.values as ArrayList<CommentsModel>
                notifyDataSetChanged()
            }
        }
    }
}