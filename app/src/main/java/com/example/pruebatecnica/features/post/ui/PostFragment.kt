package com.example.pruebatecnica.features.post.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val postViewModel : PostViewModel by viewModel()
    private var postAdapter : PostAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initObservers()
        postViewModel.getPost()
        _binding?.searchViewPost?.isSubmitButtonEnabled = true
        performSearch()
    }

    private fun initObservers(){
        postViewModel.postLiveData.observe(this, {
            it?.let {
                if (postAdapter == null) {
                    postAdapter = PostAdapter(it, postViewModel)
                    initRecyclerView()
                } else{
                    initRecyclerView()
                }
            }
        })

        postViewModel.goToAlbum.observe(this, {
            val bundle = Bundle()
            bundle.putInt("id", it)
            findNavController().navigate(R.id.albumFragment, bundle)
        })
        postViewModel.goToComments.observe(this, {
            val bundle = Bundle()
            bundle.putInt("id", it)
            findNavController().navigate(R.id.commentsFragment, bundle)
        })
    }

    private fun initRecyclerView(){
        _binding?.recyclerViewPost?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        _binding?.recyclerViewPost?.adapter = postAdapter
    }



    private fun performSearch() {
        binding.searchViewPost.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                postAdapter!!.filter.filter(newText)
                return false
            }
        })
    }


}