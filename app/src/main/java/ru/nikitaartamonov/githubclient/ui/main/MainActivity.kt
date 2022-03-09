package ru.nikitaartamonov.githubclient.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.data.intent_service.ReferenceToastIntentService
import ru.nikitaartamonov.githubclient.databinding.ActivityMainBinding
import ru.nikitaartamonov.githubclient.ui.pages.users_list.UsersListFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToastButton()
        if (savedInstanceState == null) {
            initStartScreen()
        }
    }

    private fun initStartScreen() {
        supportFragmentManager.commit {
            add(R.id.main_fragment_container, UsersListFragment())
        }
    }

    private fun initToastButton() {
        binding.toastButton.setOnClickListener {
            ReferenceToastIntentService.showToast(
                this,
                UUID.randomUUID().toString(),
                3_000L
            )
        }
    }
}