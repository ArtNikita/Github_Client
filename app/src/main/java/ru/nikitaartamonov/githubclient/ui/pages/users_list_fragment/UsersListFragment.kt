package ru.nikitaartamonov.githubclient.ui.pages.users_list_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.app
import ru.nikitaartamonov.githubclient.databinding.FragmentUsersListBinding
import ru.nikitaartamonov.githubclient.ui.pages.users_list_fragment.recycler_view.UsersListAdapter

class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val viewModel: UsersListContract.ViewModel by viewModels<UsersListViewModel>()

    private val adapter = UsersListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.usersList = app.usersList
        binding.usersListRecyclerView.adapter = adapter
    }
}