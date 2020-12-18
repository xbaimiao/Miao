package com.xbaimiao.miao.channel

import com.xbaimiao.miao.BungeeMiao
import com.xbaimiao.miao.bungee.channel.ChannelType.*
import net.md_5.bungee.BungeeTitle
import net.md_5.bungee.api.chat.TextComponent
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Server(private val socket: Socket) : Runnable {

    private val plugin = BungeeMiao.plugin
    private val ip = socket.inetAddress.toString().substring(1)
    private val input: InputStream = socket.getInputStream()
    private val output = socket.getOutputStream()

    override fun run() {
        if (isLocal()) {
            val message = read()
            plugin.logger.info("from $ip/command | $message")
            val args = message.split(" ")
            try {
                when (valueOf(args[0])) {

                    ALL_PLAYER -> send(plugin.proxy.players.size)

                    ALL_PLAYER_LIST -> send(ArrayList(plugin.proxy.players).toString())

                    ALL_SERVER -> send(ArrayList(plugin.proxy.serversCopy.keys).toString())

                    SERVER -> {
                        val servers = plugin.proxy.serversCopy
                        val serverName = args[1]
                        if (servers[serverName] == null) {
                            send("null")
                        }
                        send(servers[serverName]!!.toString())
                    }
                    IP -> send(plugin.proxy.getPlayer(args[1]).address.hostName)

                    PLAYER_COUNT -> send(plugin.proxy.getServerInfo(args[1]).players.size)

                    PLAYER_LIST -> send(plugin.proxy.getServerInfo(args[1]).players.toString())

                    MESSAGE -> {
                        if (args[2] == "") {
                            send("no")
                            return
                        }
                        plugin.proxy.getPlayer(args[1]).sendMessage(args[2])
                        sendOK()
                    }

                    UUID -> send(plugin.proxy.getPlayer(args[1]).uniqueId.toString())

                    TITLE -> {
                        val title = BungeeTitle()
                                .title(TextComponent(args[2]))
                                .subTitle(TextComponent(args[3]))
                        plugin.proxy.getPlayer(args[1]).sendTitle(title)
                        sendOK()
                    }
                }
            } catch (e: Exception) {
                plugin.logger.warning("$args command error")
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
        return stringBuilder.toString()
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

    private fun sendOK(){
        send("true")
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