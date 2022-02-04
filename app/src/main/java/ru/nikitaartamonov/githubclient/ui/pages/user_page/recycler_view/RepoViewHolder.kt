package ru.nikitaartamonov.githubclient.ui.pages.user_page.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.databinding.RepoRecyclerViewItemBinding
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity

class RepoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.repo_recycler_view_item, parent, false)
) {

    private val binding = RepoRecyclerViewItemBinding.bind(itemView)

    fun bind(repo: RepoEntity) {
        binding.repoNameTextView.text = repo.name
    }
}