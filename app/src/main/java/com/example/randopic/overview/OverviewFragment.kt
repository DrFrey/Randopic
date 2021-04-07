package com.example.randopic.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.randopic.PictureFilterValues
import com.example.randopic.R
import com.example.randopic.databinding.FragmentOverviewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    private val adapter = PictureDataAdapter(PictureDataAdapter.OnClickListener {
        viewModel.displayPictureDetails(it)
    })

    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initAdapter()

        viewModel.navigateToSelectedPicture.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it))
                viewModel.displayPictureDetailsComplete()
            }
        })
        binding.retryButton.setOnClickListener { adapter.retry() }

        refreshAdapter()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getPictures(
            when (item.itemId) {
                R.id.item_popular -> PictureFilterValues.POPULAR
                R.id.item_latest -> PictureFilterValues.LATEST
                R.id.item_oldest -> PictureFilterValues.OLDEST
                else -> PictureFilterValues.RANDOM
            }
        )
        refreshAdapter()
        return true
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PicLoadStateAdapter { adapter.retry() },
            footer = PicLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            binding.errorTv.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                binding.errorTv.text = it.error.localizedMessage
            }
        }
    }

    private fun refreshAdapter() {
        lifecycleScope.launch {
            viewModel.picturesList?.collect {
                adapter.submitData(it)
            }
        }
    }
}
