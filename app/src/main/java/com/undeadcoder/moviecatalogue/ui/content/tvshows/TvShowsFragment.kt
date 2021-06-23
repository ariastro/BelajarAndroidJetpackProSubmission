package com.undeadcoder.moviecatalogue.ui.content.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undeadcoder.moviecatalogue.base.BaseFragment
import com.undeadcoder.moviecatalogue.data.TvShowEntity
import com.undeadcoder.moviecatalogue.databinding.FragmentTvShowsBinding
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue.utils.Constants.TV_SHOWS
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : BaseFragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentTvShowsBinding
    private val viewModel: TvShowsViewModel by viewModel()
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            setLoading(true)
            adapter = TvShowAdapter(this)
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                setLoading(false)
                adapter.setTvShows(tvShows)
                adapter.notifyDataSetChanged()
            })
            binding.rvTvShows.setHasFixedSize(true)
            binding.rvTvShows.adapter = adapter
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            progress.show()
            binding.rvTvShows.visibility = View.GONE
        } else {
            progress.dismiss()
            binding.rvTvShows.visibility = View.VISIBLE
        }
    }

    override fun onItemClicked(tvShowEntity: TvShowEntity) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, tvShowEntity.id.toString())
            putExtra(EXTRA_DATA_TYPE, TV_SHOWS)
        })
    }

}