package com.undeadcoder.moviecatalogue.ui.content.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.base.BaseFragment
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.databinding.FragmentTvShowsBinding
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue.ui.main.MainActivity
import com.undeadcoder.moviecatalogue.utils.Constants.TV_SHOWS
import com.undeadcoder.moviecatalogue.vo.Status
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
            (activity as MainActivity).setToolbarTitle(getString(R.string.popular_tv_show))

            setLoading(true)
            adapter = TvShowAdapter(this)
            viewModel.getTvShows().observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        setLoading(false)
                        adapter.submitList(it.data)
                        adapter.notifyDataSetChanged()
                    }
                    Status.LOADING -> setLoading(true)
                    Status.ERROR -> {
                        setLoading(false)
                        Toast.makeText(context, getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                    }
                }
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