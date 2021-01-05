package com.xbaimiao.miao.utils.economics

import org.black_ixx.playerpoints.PlayerPoints
import org.black_ixx.playerpoints.PlayerPointsAPI
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

object PlayerPoints {

    var pointsAPI: PlayerPointsAPI = (Bukkit.getServer().pluginManager.getPlugin("PlayerPoints") as PlayerPoints?)!!.api

    /**
     * 玩家剩余多少 点券
     */
    @JvmStatic
    fun OfflinePlayer.getPoints(): Int {
        return pointsAPI.look(this.uniqueId)
    }

    @JvmStatic
    fun OfflinePlayer.givePoints(num: Int) {
        pointsAPI.give(this.uniqueId, num)
    }

    @JvmStatic
    @Deprecated(
        "请使用 OfflinePlayer.givePoints(num:Int)",
        ReplaceWith("Miao.pointsAPI.give(name, num)", "com.xbaimiao.miao.Miao")
    )
    fun givePoints(num: Int, name: String) {
        pointsAPI.give(name, num)
    }

    @JvmStatic
    fun OfflinePlayer.hasPoints(num: Int): Boolean {
        return num <= this.getPoints()
    }

    @JvmStatic
    fun OfflinePlayer.takePoints(num: Int) {
        pointsAPI.take(this.uniqueId, num)
    }

    @JvmStatic
    fun OfflinePlayer.setPoints(num: Int) {
        pointsAPI.set(this.uniqueId, num)
    }

}