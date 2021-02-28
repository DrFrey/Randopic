package com.example.randopic

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


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

@BindingAdapter("imageInTextView")
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

@BindingAdapter("formattedDateTime")
fun setFormattedDateTime(textView: TextView, dateString: String?) {
    try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val inputDate = LocalDateTime.parse(dateString, inputFormatter)
        textView.text = inputDate.format((DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)))
    } catch (e: Exception) {
        textView.text = dateString
    }
}