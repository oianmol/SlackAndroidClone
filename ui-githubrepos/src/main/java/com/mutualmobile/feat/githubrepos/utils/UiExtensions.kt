package com.mutualmobile.feat.githubrepos.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
fun ImageView.loadImageFromUrl(
  url: String? = null,
  placeHolderResId: Int? = null,
  placeHolderDrawable: Drawable? = null,
  withTransition: Boolean = false,
  enableThumbnail: Boolean = false,
  roundedCorner: Int? = null
) {
  Glide.with(this)
    .load(url)
    .apply {
      placeHolderDrawable?.let { placeholder(placeHolderDrawable) }
      placeHolderResId?.let { placeholder(it) }
      if (withTransition) {
        transition(DrawableTransitionOptions.withCrossFade())
      }
      if (enableThumbnail) {
        thumbnail(0.5f)
      }
      if (roundedCorner != null) {
        val options = RequestOptions()
        options.transform(CenterCrop(), RoundedCorners(roundedCorner))
        apply(options)
      } else {
        centerCrop()
      }
    }
    .into(this)
}