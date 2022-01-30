package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.databinding.FragmentUserPageBinding
import ru.nikitaartamonov.githubclient.domain.entity.UserEntity
import java.lang.IllegalStateException
import java.lang.StringBuilder

private const val USER_NAME_KEY = "USER_NAME_KEY"

class UserPageFragment : Fragment(R.layout.fragment_user_page) {

    private val binding by viewBinding(FragmentUserPageBinding::bind)
    private val viewModel: UserPageContract.ViewModel by viewModels<UserPageViewModel>()

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
            binding.otherInfoTextView.text = generateOtherUserInfo(userEntity)
        }
    }

    private fun generateOtherUserInfo(userEntity: UserEntity): String {
        val sb = StringBuilder("|")
        userEntity.apply {
            if (company != null) sb.append(company).append("|")
            if (blog != "") sb.append(blog).append("|")
            if (location != null) sb.append(location).append("|")
            if (email != null) sb.append(email).append("|")
            if (hireable != null) sb.append(hireable).append("|")
            if (bio != null) sb.append(bio).append("|")
            if (twitter_username != null) sb.append(twitter_username).append("|")
            sb.append(public_repos).append("|")
            sb.append(public_gists).append("|")
            sb.append(followers).append("|")
            sb.append(following).append("|")
            sb.append(created_at).append("|")
            sb.append(updated_at).append("|")
        }
        return sb.toString()
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