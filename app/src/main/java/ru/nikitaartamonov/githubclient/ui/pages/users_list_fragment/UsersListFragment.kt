package ru.nikitaartamonov.githubclient.ui.pages.users_list_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.databinding.FragmentUsersListBinding

class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val viewModel: UsersListContract.ViewModel by viewModels<UsersListViewModel>()

}