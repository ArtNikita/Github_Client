package ru.nikitaartamonov.githubclient.ui.pages.users_list_fragment.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersListAdapter(private val listener: OnUserClickListener) :
    RecyclerView.Adapter<UserViewHolder>() {

    var usersList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount() = usersList.size
}