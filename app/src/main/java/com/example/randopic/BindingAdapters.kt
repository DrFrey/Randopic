package com.example.randopic

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("userpicUrl")
fun loadImageIntoTextview(textView: TextView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(textView.context)
            .load(url)
            .circleCrop()
            .into(object : CustomTarget<Drawable>(100, 100) {
                override fun onLoadCleared(drawable: Drawable?) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                }

                override fun onResourceReady(
                    res: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                ) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(res, null, null, null)
                }
            })
    }
}