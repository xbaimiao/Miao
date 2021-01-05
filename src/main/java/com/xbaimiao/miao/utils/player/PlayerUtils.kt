package com.xbaimiao.miao.utils.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player

object PlayerUtils {

    private fun getServerVersion(): String {
        val packageName = Bukkit.getServer().javaClass.getPackage().name
        val args = packageName.split(".")
        return args[3]
    }

    fun Player.sendAction(string: String) {
        val version = getServerVersion()
        if (version.startsWith("v1_16")) {
            Nms.sendAction_1_16(this, string)
        } else {
            Nms.sendAction_1_15(this, string)
        }
    }
}