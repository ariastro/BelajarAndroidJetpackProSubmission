package com.undeadcoder.moviecatalogue1.ui.content.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.undeadcoder.moviecatalogue1.data.DataEntity
import com.undeadcoder.moviecatalogue1.databinding.FragmentMoviesBinding
import com.undeadcoder.moviecatalogue1.ui.content.ContentAdapter
import com.undeadcoder.moviecatalogue1.ui.content.ContentCallback
import com.undeadcoder.moviecatalogue1.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue1.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue1.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue1.utils.Constants.MOVIES

class MoviesFragment : Fragment(), ContentCallback {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: ContentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = viewModel.getMovies()
            adapter = ContentAdapter(this)
            adapter.setMovies(movies)
            binding.rvMovies.setHasFixedSize(true)
            binding.rvMovies.adapter = adapter
        }
    }

    override fun onItemClicked(dataEntity: DataEntity) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, dataEntity.id)
            putExtra(EXTRA_DATA_TYPE, MOVIES)
        })
    }

}