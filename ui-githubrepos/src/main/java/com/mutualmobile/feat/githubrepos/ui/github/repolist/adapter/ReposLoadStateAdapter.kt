package com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mutualmobile.feat.githubrepos.databinding.ReposLoadStateFooterBinding
import com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter.ReposLoadStateAdapter.LoadStateViewHolder

class ReposLoadStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    loadState: LoadState
  ): LoadStateViewHolder {
    val binding = ReposLoadStateFooterBinding
      .inflate(LayoutInflater.from(parent.context), parent, false)
    return LoadStateViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: LoadStateViewHolder,
    loadState: LoadState
  ) {
    holder.bind(loadState)
  }

  inner class LoadStateViewHolder(private val binding: ReposLoadStateFooterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
      binding.progress.isVisible = loadState is LoadState.Loading
    }
  }
}