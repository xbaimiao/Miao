package com.xbaimiao.miao.utils.player

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

object Nms {

    @Throws(ClassNotFoundException::class)
    fun getNmsClass(Name: String): Class<*> {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().javaClass.getPackage().name.replace(".", ",").split(",".toRegex()).toTypedArray()[3] + "." + Name)
    }

    val serverVersion: String
        get() = Bukkit.getServer().javaClass.getPackage().name.substring(23)

    fun sendAction_1_16(p: Player, msg: String?) {
        try {
            val icbc = getNmsClass("ChatComponentText").getConstructor(*arrayOf<Class<*>>(String::class.java))
                    .newInstance(ChatColor.translateAlternateColorCodes('&', msg!!))
            val cmt = getNmsClass("ChatMessageType").getField("GAME_INFO")[null]
            val ppoc = getNmsClass("PacketPlayOutChat")
                    .getConstructor(*arrayOf(getNmsClass("IChatBaseComponent"), getNmsClass("ChatMessageType"), UUID::class.java))
                    .newInstance(icbc, cmt, p.uniqueId)
            val nmsp = p.javaClass.getMethod("getHandle", *arrayOfNulls(0)).invoke(p)
            val pcon = nmsp.javaClass.getField("playerConnection")[nmsp]
            pcon.javaClass.getMethod("sendPacket", *arrayOf(getNmsClass("Packet"))).invoke(pcon,
                    ppoc)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendAction_1_15(p: Player, msg: String?) {
        try {
            val icbc = getNmsClass("ChatComponentText").getConstructor(*arrayOf<Class<*>>(String::class.java))
                    .newInstance(ChatColor.translateAlternateColorCodes('&', msg!!))
            val cmt = getNmsClass("ChatMessageType").getField("GAME_INFO")[null]
            val ppoc = getNmsClass("PacketPlayOutChat")
                    .getConstructor(*arrayOf(getNmsClass("IChatBaseComponent"), getNmsClass("ChatMessageType")))
                    .newInstance(icbc, cmt)
            val nmsp = p.javaClass.getMethod("getHandle", *arrayOfNulls(0)).invoke(p)
            val pcon = nmsp.javaClass.getField("playerConnection")[nmsp]
            pcon.javaClass.getMethod("sendPacket", *arrayOf(getNmsClass("Packet"))).invoke(pcon,
                    ppoc)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}