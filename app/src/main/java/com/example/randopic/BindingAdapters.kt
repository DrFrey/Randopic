package com.example.randopic

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randopic.data.Picture
import com.example.randopic.overview.PictureDataAdapter
import kotlinx.coroutines.Job

/*@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PagingData<Picture>?) {
    val adapter = recyclerView.adapter as PictureDataAdapter
    adapter.submitData(data)
}*/

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}