package com.xbaimiao.miao.channel

import com.xbaimiao.miao.BungeeMiao
import com.xbaimiao.miao.bungee.ChannelType.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Server(val socket: Socket) : Runnable {

    private val plugin = BungeeMiao.plugin
    private val ip = socket.inetAddress.toString().substring(1)
    private val input: InputStream = socket.getInputStream()
    private val output = socket.getOutputStream()

    override fun run() {
        if (isLocal()) {
            val message = read()
            plugin.logger.info("from $ip/command | $message")
            val args = message.split(":")
            try {
                when (valueOf(args[0])) {
                    ALL_PLAYER -> send(plugin.proxy.players.size)
                    ALL_PLAYER_LIST -> {
                        val players = ArrayList(plugin.proxy.players)
                        send(players.toString())
                    }
                    ALL_SERVER -> {
                        val servers = ArrayList(plugin.proxy.serversCopy.keys)
                        send(servers.toString())
                    }
                    SERVER -> {
                        val servers = plugin.proxy.serversCopy
                        val serverName = args[1]
                        if (servers[serverName] == null) {
                            send("null")
                        }
                        send(servers[serverName]!!.toString())
                    }
                    IP -> {
                        val playerName = args[1]
                        send(plugin.proxy.getPlayer(playerName).address.hostName)
                    }
                    PLAYER_COUNT -> {
                        val serverName = args[1]
                        send(plugin.proxy.getServerInfo(serverName).players.size)
                    }
                    PLAYER_LIST -> {
                        val serverName = args[1]
                        send(plugin.proxy.getServerInfo(serverName).players.toString())
                    }
                    MESSAGE -> {
                        if (args[2] == "") {
                            send("no")
                            return
                        }
                        val playerName = args[1]
                        plugin.proxy.getPlayer(playerName).sendMessage(args[2])
                        send("ok")
                    }
                    UUID -> {
                        val playerName = args[1]
                        send(plugin.proxy.getPlayer(playerName).uniqueId.toString())
                    }
                }
            } catch (e: Exception) {
                plugin.logger.warning("$message command error")
                close()
            }
        } else close()
    }

    private fun read(): String {
        val br = BufferedReader(InputStreamReader(input)) //解析浏览器请求的地址
        val stringBuilder = StringBuilder()
        var info: String?
        while (br.readLine().also { info = it } != null) { //循环读取客户端的信息
            stringBuilder.append(info)
        }
        return stringBuilder.toString().toUpperCase()
    }

    private fun send(string: String) {
        val os = socket.getOutputStream()
        val pw = PrintWriter(os)
        pw.write(string)
        pw.flush()
        socket.shutdownOutput()
    }

    private fun send(num: Int) {
        send(num.toString())
    }

    private fun isLocal(): Boolean {
        return ip == "127.0.0.1"
    }

    private fun close() {
        input.close()
        output.close()
        socket.close()
    }

}