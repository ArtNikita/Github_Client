package ru.nikitaartamonov.githubclient.data.intent_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast

private const val MESSAGE_KEY = "MESSAGE_KEY"
private const val DELAY_KEY = "DELAY_KEY"
private const val PRIORITY_KEY = "PRIORITY_KEY"

class CustomToastIntentService : Service() {

    private var counter = 0

    private val handlerThread: CustomHandlerThread = CustomHandlerThread("Handler thread")

    override fun onCreate() {
        super.onCreate()
        handlerThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val msg = it.getStringExtra(MESSAGE_KEY) ?: ""
            val delay = it.getLongExtra(DELAY_KEY, 0)
            val priority = it.getIntExtra(PRIORITY_KEY, 0)
            handlerThread.post(priority) {
                showDelayedToast(msg, delay, priority)
            }
        }
        return START_REDELIVER_INTENT
    }

    private fun showDelayedToast(msg: String, delay: Long, priority: Int) {
        val mainHandler = Handler(Looper.getMainLooper())
        Thread.sleep(delay)
        val countedMsg = "${counter++}. $priority. $msg"
        mainHandler.post {
            Toast.makeText(this, countedMsg, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        fun showToast(context: Context, msg: String, delay: Long, priority: Int) {
            val intent = Intent(context, CustomToastIntentService::class.java)
            intent.putExtra(MESSAGE_KEY, msg)
            intent.putExtra(DELAY_KEY, delay)
            intent.putExtra(PRIORITY_KEY, priority)
            context.startService(intent)
        }
    }
}