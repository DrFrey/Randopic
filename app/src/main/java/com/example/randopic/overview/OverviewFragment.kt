package com.example.randopic.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.randopic.PictureFilterValues
import com.example.randopic.R
import com.example.randopic.databinding.FragmentOverviewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OverviewFragment: Fragment() {
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    private val adapter = PictureDataAdapter()
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.viewPager.adapter = adapter

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

    private fun refreshAdapter() {
        lifecycleScope.launch {
            viewModel.picturesList?.collect {
                adapter.submitData(it)
            }
        }
    }
}
