package ru.nikitaartamonov.githubclient.ui.pages.users_list_fragment.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.databinding.UserRecyclerViewItemBinding

class UserViewHolder(parent: ViewGroup, listener: OnUserClickListener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_recycler_view_item, parent, false)
) {

    init {
        itemView.setOnClickListener { listener.onClick(currentUserName) }
    }

    private val binding = UserRecyclerViewItemBinding.bind(itemView)
    private var currentUserName = ""

    fun bind(userName: String) {
        currentUserName = userName
        binding.recyclerViewUserNameTextView.text = userName
    }
}