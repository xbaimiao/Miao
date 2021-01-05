package com.xbaimiao.miao.bungee.channel

import com.xbaimiao.miao.bungee.type.BungeeChannel
import com.xbaimiao.miao.bungee.type.PlayerChannel
import com.xbaimiao.miao.bungee.type.ServerChannel
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class Channel(val host: String, val port: Int) {

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

    /**
     * 获取指定服务器的信息
     */
    fun getServer(server: String) = ServerChannel(this, server)

    /**
     * 获取玩家信息
     */
    fun getPlayer(name: String) = PlayerChannel(this, name)

    /**
     * 获取Bungee的信息
     */
    fun getBungee() = BungeeChannel(this)

}