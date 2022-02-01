package ru.nikitaartamonov.githubclient.ui.pages.user_page.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.githubclient.domain.entity.RepoEntity

class ReposListAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    var reposList = listOf<RepoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(reposList[position])
    }

    override fun getItemCount() = reposList.size
}