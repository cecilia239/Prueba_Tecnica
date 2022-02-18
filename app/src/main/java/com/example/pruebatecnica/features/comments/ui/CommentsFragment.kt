package com.example.pruebatecnica.features.comments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.databinding.FragmentCommentsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsFragment : Fragment() {
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private val commentsViewModel: CommentsViewModel by viewModel()
    private var commentsAdapter: CommentsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initObservers()
        arguments?.let {
            commentsViewModel.getComments(it.getInt("id"))
        }
        performSearch()
    }

    private fun initObservers() {
        commentsViewModel.commentsLiveData.observe(this, {
            it.let {
                if (commentsAdapter == null) {
                    commentsAdapter = CommentsAdapter(it)
                    initRecyclerView()
                } else {
                    initRecyclerView()
                }
            }
        })
    }

    private fun initRecyclerView() {
        _binding?.recyclerViewComments?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        _binding?.recyclerViewComments?.adapter = commentsAdapter
    }

    private fun performSearch() {
        binding.searchViewComments.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                commentsAdapter!!.filter.filter(newText)
                return false
            }
        })
    }

}