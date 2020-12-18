package com.xbaimiao.miao.bungee.channel

import com.xbaimiao.miao.bungee.type.MessageChannel
import com.xbaimiao.miao.bungee.type.TitleChannel
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.net.Socket

class Channel(private val host: String, private val port: Int) {

    constructor() : this("127.0.0.1", 22223)

    lateinit var socket: Socket
    var isShutdowm = false
    var isConnect = false

    fun getOutputStream(): OutputStream {
        return socket.getOutputStream()
    }

    fun getInputStream(): InputStream {
        return socket.getInputStream()
    }

    /**
     * 建立连接
     */
    fun connect(): Channel {
        if (isConnect) {
            return this
        }
        socket = Socket(host, port)
        isConnect = true
        return this
    }

    private fun send(type: ChannelType): Channel {
        connect()
        val pw = PrintWriter(getOutputStream())
        pw.write(type.name)
        pw.flush()
        return this
    }

    /**
     * 发送TitleChannel
     */
    fun sendTitle(player: String, title: String, subTitle: String): TitleChannel = TitleChannel(send(ChannelType.TITLE), player, title, subTitle)

    fun sendMessage(name: String, msg: String): MessageChannel = MessageChannel((send(ChannelType.MESSAGE)), name, msg)

}

fun main() {
    println(Channel().sendTitle("xbaimiao", "111", "222").isSuccess)
}