package ru.nikitaartamonov.githubclient.data.intent_service

import androidx.annotation.WorkerThread
import java.util.*

class CustomHandlerThread(name: String) : Thread(name) {

    private val queue: Queue<Runnable> = LinkedList()
    private var isRunning = false
    private val lock: Object = Object()

    @WorkerThread
    override fun run() {
        isRunning = true
        while (isRunning) {
            val runnable: Runnable?
            synchronized(lock) {
                runnable = queue.poll()
                if (runnable == null) {
                    lock.wait()
                }
            }
            runnable?.run()
        }
    }

    fun quit() {
        synchronized(lock) {
            isRunning = false
            lock.notify()
        }
    }

    fun post(runnable: Runnable) {
        synchronized(lock) {
            queue.add(runnable)
            lock.notify()
        }
    }
}