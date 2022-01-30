package ru.nikitaartamonov.githubclient.ui.pages.user_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.nikitaartamonov.githubclient.R

private const val USER_NAME_KEY = "USER_NAME_KEY"

class UserPageFragment : Fragment(R.layout.fragment_user_page) {

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