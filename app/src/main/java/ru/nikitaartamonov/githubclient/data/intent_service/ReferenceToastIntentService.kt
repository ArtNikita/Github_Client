package ru.nikitaartamonov.githubclient.data.intent_service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast

private const val MESSAGE_KEY = "MESSAGE_KEY"
private const val DELAY_KEY = "DELAY_KEY"

class ReferenceToastIntentService : IntentService("ReferenceToastIntentService") {

    private var counter = 0

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val msg = it.getStringExtra(MESSAGE_KEY) ?: ""
            val delay = it.getLongExtra(DELAY_KEY, 0)
            showDelayedToast(msg, delay)
        }
    }

    private fun showDelayedToast(msg: String, delay: Long) {
        val mainHandler = Handler(Looper.getMainLooper())
        Thread.sleep(delay)
        val countedMsg = "${counter++}. $msg"
        mainHandler.post {
            Toast.makeText(this, countedMsg, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        fun showToast(context: Context, msg: String, delay: Long) {
            val intent = Intent(context, ReferenceToastIntentService::class.java)
            intent.putExtra(MESSAGE_KEY, msg)
            intent.putExtra(DELAY_KEY, delay)
            context.startService(intent)
        }
    }
}