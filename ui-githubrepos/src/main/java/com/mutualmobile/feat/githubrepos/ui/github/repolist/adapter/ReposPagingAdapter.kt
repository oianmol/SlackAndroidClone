package com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mutualmobile.feat.githubrepos.databinding.ItemTrendingRepoBinding
import com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter.ReposPagingAdapter.ViewHolder
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.feat.githubrepos.utils.loadImageFromUrl

class ReposPagingAdapter(
  private val repoClickListener: RepoClickListener
) : PagingDataAdapter<UIRepo, ViewHolder>(
  REPO_COMPARATOR
) {

  companion object {
    private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<UIRepo>() {
      override fun areItemsTheSame(
        oldItem: UIRepo,
        newItem: UIRepo
      ) = oldItem.id == newItem.id

      override fun areContentsTheSame(
        oldItem: UIRepo,
        newItem: UIRepo
      ) = oldItem == newItem
    }
  }

  interface RepoClickListener {
    fun onRepoItemClicked(uiRepo: UIRepo)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val binding =
      ItemTrendingRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    getItem(position).let { repo ->
      with(holder) {
        itemView.tag = repo
        if (repo != null) {
          bind(createOnClickListener(binding, repo), repo)
        }
      }
    }
  }

  private fun createOnClickListener(
    binding: ItemTrendingRepoBinding,
    repo: UIRepo
  ): View.OnClickListener {
    return View.OnClickListener {
      repoClickListener.onRepoItemClicked(repo)
    }
  }

  class ViewHolder(val binding: ItemTrendingRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
      listener: View.OnClickListener,
      repo: UIRepo
    ) {
      binding.apply {
        ivAvatar.loadImageFromUrl(
          url = repo.owner.avatarUrl,
          roundedCorner = 5,
          enableThumbnail = true
        )
        with(repo) {
          tvName.text = name
          tvDescription.text = description
          tvLanguage.text = language
        }
        root.setOnClickListener(listener)
      }
    }
  }
}