package com.xbaimiao.miao.bungee

import java.io.*
import java.net.InetAddress
import java.net.Socket


class Channel(private val host: String, private val port: Int) {

    constructor(): this("127.0.0.1",22223)

    private lateinit var socket: Socket
    private var ip: String? = null
    var isShutdowm = false

    private fun getOutputStream(): OutputStream {
        return socket.getOutputStream()
    }

    private fun getInputStream(): InputStream {
        return socket.getInputStream()
    }

    fun connect(): Channel {
        socket = Socket(host, port)
        return this
    }

    fun getLocalIp(): String {
        if (ip == null) {
            ip = InetAddress.getLocalHost().hostAddress
            return ip!!
        }
        return ip!!
    }

    fun send(type: ChannelType): Channel {
        val pw = PrintWriter(getOutputStream())
        pw.write(type.name)
        pw.flush()
        return this
    }

    fun writeUTF(string: String): Channel {
        val pw = PrintWriter(getOutputStream())
        pw.write(":$string")
        pw.flush()
        return this
    }

    fun shutdown(): Channel{
        isShutdowm = true
        socket.shutdownOutput()
        return this
    }

    fun readUTF(): String {
        if (!isShutdowm){
            shutdown()
        }
        val br = BufferedReader(InputStreamReader(getInputStream())) //解析浏览器请求的地址
        val stringBuilder = StringBuilder()
        var info: String?
        while (br.readLine().also { info = it } != null) { //循环读取客户端的信息
            stringBuilder.append(info)
        }
        return stringBuilder.toString()
    }

//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val players = ChannelUtils.listFromString(Channel().connect().send(ChannelType.ALL_PLAYER_LIST).readUTF())
//            println(Channel().connect().send(ChannelType.ALL_PLAYER_LIST).readUTF())
//            players.forEach {
//                val msg = Channel()
//                        .connect()
//                        .send(ChannelType.MESSAGE)
//                        .writeUTF(it)
//                        .writeUTF("傻逼")
//                        .shutdown()
//                        .readUTF()
//                println(msg)
//            }
//
//        }
//    }
}