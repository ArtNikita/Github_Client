package ru.nikitaartamonov.githubclient.ui.pages.users_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.app
import ru.nikitaartamonov.githubclient.databinding.FragmentUsersListBinding
import ru.nikitaartamonov.githubclient.ui.pages.user_page.UserPageFragment
import ru.nikitaartamonov.githubclient.ui.pages.users_list.recycler_view.OnUserClickListener
import ru.nikitaartamonov.githubclient.ui.pages.users_list.recycler_view.UsersListAdapter

class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val viewModel: UsersListContract.ViewModel by viewModels<UsersListViewModel>()

    private val adapter = UsersListAdapter(
        object : OnUserClickListener {
            override fun onClick(userName: String) {
                viewModel.onUserClick(userName)
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.openUserPageLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { userName ->
                requireActivity().supportFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.main_fragment_container, UserPageFragment.newInstance(userName))
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        app.usersList.getAllUserNames()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { adapter.usersList = it }
        binding.usersListRecyclerView.adapter = adapter
    }
}