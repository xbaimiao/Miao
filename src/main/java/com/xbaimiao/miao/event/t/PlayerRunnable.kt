package com.xbaimiao.miao.event.t

import com.xbaimiao.miao.Miao
import com.xbaimiao.miao.event.PlayerInWater
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.scheduler.BukkitRunnable

/**
 * @Auther: 小白
 * @Date: 2021/1/2 09:36
 * @Description:
 */
class PlayerRunnable : BukkitRunnable() {

    override fun run() {
        val players = Bukkit.getOnlinePlayers()
        if (players.isEmpty()) {
            return
        }
        players.forEach {
            val location = it.location
            if (location.block.type == Material.WATER) {
                val event = PlayerInWater(it)
                Bukkit.getScheduler().runTask(Miao.instance, Runnable { Bukkit.getPluginManager().callEvent(event) })
            }
        }
    }

}