package com.xbaimiao.miao.bungee

import java.io.*
import java.net.InetAddress
import java.net.Socket


class Channel(private val host: String, private val port: Int) {

    constructor(): this("127.0.0.1",22223)

    private lateinit var socket: Socket
    private var ip: String? = null
    private var isShutdowm = false

    private fun getOutputStream(): OutputStream {
        return socket.getOutputStream()
    }

    private fun getInputStream(): InputStream {
        return socket.getInputStream()
    }

    /**
     * 建立连接
     */
    fun connect(): Channel {
        socket = Socket(host, port)
        return this
    }

    /**
     * 获取本地ip地址
     */
    fun getLocalIp(): String {
        if (ip == null) {
            ip = InetAddress.getLocalHost().hostAddress
            return ip!!
        }
        return ip!!
    }

    /**
     * 发送Channel
     */
    fun send(type: ChannelType): Channel {
        val pw = PrintWriter(getOutputStream())
        pw.write(type.name)
        pw.flush()
        return this
    }

    /**
     * 发送 附加参数
     */
    fun writeUTF(string: String): Channel {
        val pw = PrintWriter(getOutputStream())
        pw.write(":$string")
        pw.flush()
        return this
    }

    /**
     * 关闭输出流
     */
    fun shutdown(): Channel{
        isShutdowm = true
        socket.shutdownOutput()
        return this
    }

    /**
     * 读取bungee的返回值
     */
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

}