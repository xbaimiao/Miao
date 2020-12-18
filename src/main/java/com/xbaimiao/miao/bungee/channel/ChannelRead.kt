package com.xbaimiao.miao.bungee.channel

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetAddress

open class ChannelRead(private val channel: Channel) {

    companion object {
        @JvmStatic
        fun String.listFromString(): List<String> {
            val list = this.substring(1, this.length - 1).replace(" ", "")
            return ArrayList(list.split(","))
        }
    }

    fun getChannelRead(): ChannelRead = ChannelRead(Channel(channel.host, channel.port))

    /**
     * 关闭输出流
     */
    private fun shutdown() {
        channel.isShutdowm = true
        channel.socket.shutdownOutput()
    }

    /**
     * 读取bungee的返回值
     */
    fun readUTF(): String {
        if (!channel.isShutdowm) {
            shutdown()
        }
        val br = BufferedReader(InputStreamReader(channel.getInputStream())) //解析浏览器请求的地址
        val stringBuilder = StringBuilder()
        var info: String?
        while (br.readLine().also { info = it } != null) { //循环读取客户端的信息
            stringBuilder.append(info)
        }
        return stringBuilder.toString()
    }

    /**
     * 发送 附加参数
     */
    fun writeUTF(string: String): ChannelRead {
        val pw = PrintWriter(channel.getOutputStream())
        pw.write(" $string")
        pw.flush()
        return this
    }

    /**
     * 发送Channel
     */
    fun send(type: ChannelType): ChannelRead {
        channel.connect()
        val pw = PrintWriter(channel.getOutputStream())
        pw.write(type.name)
        pw.flush()
        return this
    }

    fun getLocalIp(): String = InetAddress.getLocalHost().hostAddress

}