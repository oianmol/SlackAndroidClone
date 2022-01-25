package com.mutualmobile.feat.githubrepos.utils

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
  @JvmStatic
  @BindingAdapter("visibility")
  fun setVisibility(
    view: View,
    visible: Boolean,
  ) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
  }
}