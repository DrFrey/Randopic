package com.example.randopic.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.randopic.data.Picture
import com.example.randopic.databinding.OverviewItemBinding

class PictureDataAdapter: PagingDataAdapter<Picture, PictureDataAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: OverviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            binding.picture = picture
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OverviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = getItem(position)
        if (picture != null) {
            holder.bind(picture)
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Picture>() {
        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem == newItem
        }

    }
}