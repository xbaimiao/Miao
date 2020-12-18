package com.xbaimiao.miao.bungee.utils

import com.xbaimiao.miao.Miao
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException

object Bungee {

    init {
        if (!Bukkit.getMessenger().isOutgoingChannelRegistered(Miao.instance, "BungeeCord")) {
            Bukkit.getMessenger().registerOutgoingPluginChannel(Miao.instance, "BungeeCord")
        }
    }

    /**
     * 让玩家链接至指定服务器
     */
    @JvmStatic
    fun connect(player: Player, server: String) = sendBungeeData(player, "Connect", server)

    /**
     * 踢出玩家
     */
    @JvmStatic
    fun kick(player: Player, string: String) = sendBungeeData(player, "KickPlayer", player.name, string)

    /**
     * 发送消息
     */
    fun sendMessage(player: Player, string: String) = sendBungeeData(player, "Message", player.name, string)

    private fun sendBungeeData(player: Player, vararg args: String) {
        val byteArray = ByteArrayOutputStream()
        val out = DataOutputStream(byteArray)
        for (arg in args) {
            try {
                out.writeUTF(arg)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        player.sendPluginMessage(Miao.instance, "BungeeCord", byteArray.toByteArray())
    }

}