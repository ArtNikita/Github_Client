package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.databinding.FragmentUserPageBinding

private const val USER_NAME_KEY = "USER_NAME_KEY"

class UserPageFragment : Fragment(R.layout.fragment_user_page) {

    private val binding by viewBinding(FragmentUserPageBinding::bind)
    private val viewModel: UserPageContract.ViewModel by viewModels<UserPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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