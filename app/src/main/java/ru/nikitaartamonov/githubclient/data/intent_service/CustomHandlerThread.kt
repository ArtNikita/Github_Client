package ru.nikitaartamonov.githubclient.data.intent_service

import androidx.annotation.WorkerThread
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class CustomHandlerThread(name: String) : Thread(name) {

    private val queue: Queue<Runnable> = ConcurrentLinkedQueue()
    private var isRunning = false
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    @WorkerThread
    override fun run() {
        isRunning = true
        while (isRunning) {
            val runnable: Runnable? = queue.poll()
            lock.withLock {
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
        queue.add(runnable)
        lock.withLock { condition.signal() }
    }
}