package com.xbaimiao.miao.utils

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

	@JvmStatic
	fun connect(player: Player, server: String) = sendBungeeData(player, "Connect", server)

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