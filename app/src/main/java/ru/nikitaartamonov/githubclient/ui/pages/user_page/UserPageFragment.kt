package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.databinding.FragmentUserPageBinding
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity
import ru.nikitaartamonov.githubclient.ui.pages.user_page.recycler_view.ReposListAdapter

private const val USER_NAME_KEY = "USER_NAME_KEY"

class UserPageFragment : Fragment(R.layout.fragment_user_page) {

    private val binding by viewBinding(FragmentUserPageBinding::bind)
    private val viewModel: UserPageContract.ViewModel by viewModels<UserPageViewModel>()

    private val adapter = ReposListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewModel.onViewIsReady(
            requireArguments().getString(USER_NAME_KEY)
                ?: throw IllegalStateException("User name should be provided")
        )
    }

    private fun initViewModel() {
        viewModel.renderUserLiveData.observe(viewLifecycleOwner) { userEntity ->
            Glide
                .with(requireContext())
                .load(userEntity.avatar_url)
                .circleCrop()
                .into(binding.avatarImageView)
            binding.userLoginTextView.text = userEntity.login
            binding.userNameTextView.text = userEntity.name
            binding.userIdTextView.text = userEntity.id.toString()
            binding.otherInfoTextView.text = UserEntity.generateOtherUserInfo(userEntity)
        }
        viewModel.renderReposLiveData.observe(viewLifecycleOwner) { reposList ->
            adapter.reposList = reposList
            binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.repositoriesRecyclerView.adapter = adapter
        }
    }

    companion object {
        fun newInstance(userName: String): UserPageFragment {
            val args = Bundle()
            args.putString(USER_NAME_KEY, userName)
            val fragment = UserPageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}