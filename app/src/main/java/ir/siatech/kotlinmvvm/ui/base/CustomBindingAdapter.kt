package ir.siatech.kotlinmvvm.ui.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:image_url")
    fun loadImage(imageView: AppCompatImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}