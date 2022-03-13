package ru.nikitaartamonov.githubclient.ui.pages.user_page.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity

class ReposListAdapter(var reposList: List<RepoEntity> = listOf()) :
    RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(reposList[position])
    }

    override fun getItemCount() = reposList.size
}