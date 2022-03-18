package ru.nikitaartamonov.githubclient.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named
import ru.nikitaartamonov.githubclient.R
import ru.nikitaartamonov.githubclient.data.intent_service.CustomToastIntentService
import ru.nikitaartamonov.githubclient.databinding.ActivityMainBinding
import ru.nikitaartamonov.githubclient.di.UUID_QUALIFIER
import ru.nikitaartamonov.githubclient.ui.pages.users_list.UsersListFragment

private const val TOAST_DELAY: Long = 3_000L

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToastButtons()
        if (savedInstanceState == null) {
            initStartScreen()
        }
    }

    private fun initStartScreen() {
        supportFragmentManager.commit {
            add(R.id.main_fragment_container, UsersListFragment())
        }
    }

    private fun initToastButtons() {
        binding.highPriorityToastButton.setOnClickListener {
            CustomToastIntentService.showToast(
                this,
                get(named(UUID_QUALIFIER)),
                TOAST_DELAY,
                5
            )
        }
        binding.defaultPriorityToastButton.setOnClickListener {
            CustomToastIntentService.showToast(
                this,
                get(named(UUID_QUALIFIER)),
                TOAST_DELAY,
                0
            )
        }
        binding.lowPriorityToastButton.setOnClickListener {
            CustomToastIntentService.showToast(
                this,
                get(named(UUID_QUALIFIER)),
                TOAST_DELAY,
                -5
            )
        }
    }
}