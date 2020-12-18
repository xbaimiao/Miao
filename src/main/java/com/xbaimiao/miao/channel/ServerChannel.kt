package com.xbaimiao.miao.channel

import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.*

class ServerChannel(val port: Int) {

    private val myExecutorService = ThreadPoolExecutor(
            5,10,60,
            TimeUnit.SECONDS,ArrayBlockingQueue(3), RejectedThreadPoolHandler()
    )

    fun start() {
        Thread {
            println("Channel Start ... Port:$port")
            val serverSocket = ServerSocket(port)
            var socket: Socket?
            while (serverSocket.accept().also { socket = it } != null) {
                myExecutorService.execute(Server(socket!!))
            }
        }.start()
    }

    class RejectedThreadPoolHandler : RejectedExecutionHandler {
        override fun rejectedExecution(r: Runnable, executor: ThreadPoolExecutor) {
        }
    }

}