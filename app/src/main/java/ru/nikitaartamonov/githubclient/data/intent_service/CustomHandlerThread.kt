package ru.nikitaartamonov.githubclient.data.intent_service

import androidx.annotation.WorkerThread
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class CustomHandlerThread(name: String) : Thread(name) {

    private val queue: PriorityBlockingQueue<Entity> = PriorityBlockingQueue()
    private var isRunning = false
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    @WorkerThread
    override fun run() {
        isRunning = true
        while (isRunning) {
            val runnable: Runnable? = queue.poll()?.runnable
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

    fun post(priority: Int = 0, runnable: Runnable) {
        queue.add(Entity(priority, runnable))
        lock.withLock { condition.signal() }
    }

    private data class Entity(val priority: Int, val runnable: Runnable) : Comparable<Entity> {

        override fun compareTo(other: Entity): Int = when {
            priority > other.priority -> -1
            priority == other.priority -> 0
            else -> 1
        }
    }
}