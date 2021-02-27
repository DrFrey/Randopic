package com.example.randopic.overview

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PicLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PicLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PicLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PicLoadStateViewHolder {
        return PicLoadStateViewHolder.create(parent, retry)
    }
}