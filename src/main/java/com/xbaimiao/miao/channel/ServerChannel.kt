package com.xbaimiao.miao.channel

import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ServerChannel(val port: Int) {

    private val myExecutorService: ExecutorService = Executors.newCachedThreadPool()

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

}