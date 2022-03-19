package com.jobplanet.company.util.ext

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jobplanet.company.R


@BindingAdapter("companyLogo")
fun bindCompanyLogo(imageView: ImageView, logoPath:String?) {

    val circularProgressDrawable = CircularProgressDrawable(imageView.context).apply {
        strokeWidth = 5f
        centerRadius = 30f
    }
    circularProgressDrawable.start()

    Glide.with(imageView.context)
        .load(logoPath)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.logo_failed)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                circularProgressDrawable.stop()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                circularProgressDrawable.stop()
                return false
            }
        })
        .apply(RequestOptions().override(imageView.width, imageView.height).fitCenter())
        .into(imageView)
}