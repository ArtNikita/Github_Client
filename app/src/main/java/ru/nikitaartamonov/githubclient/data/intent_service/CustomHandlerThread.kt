package ru.nikitaartamonov.githubclient.data.intent_service

import androidx.annotation.WorkerThread
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class CustomHandlerThread(name: String) : Thread(name) {

    private val queue: Queue<Runnable> = LinkedList()
    private var isRunning = false
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    @WorkerThread
    override fun run() {
        isRunning = true
        while (isRunning) {
            val runnable: Runnable?
            lock.withLock {
                runnable = queue.poll()
                if (runnable == null) {
                    condition.await()
                }
            }
            runnable?.run()
        }
    }

    fun quit() {
        lock.withLock {
            isRunning = false
            condition.signal()
        }
    }

    fun post(runnable: Runnable) {
        lock.withLock {
            queue.add(runnable)
            condition.signal()
        }
    }
}