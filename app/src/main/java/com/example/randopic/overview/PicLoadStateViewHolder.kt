package com.example.randopic.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.randopic.R
import com.example.randopic.databinding.PicLoadStateFooterViewItemBinding

class PicLoadStateViewHolder(
    private val binding: PicLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PicLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pic_load_state_footer_view_item, parent, false)
            val binding = PicLoadStateFooterViewItemBinding.bind(view)
            return PicLoadStateViewHolder(binding, retry)
        }
    }
}