package com.xbaimiao.miao.utils

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player

object Random {

    private val notBlock = ArrayList<Material>().also {
        it.add(Material.LAVA)
        it.add(Material.WATER)
        it.add(Material.AIR)
    }

    /**
     * 随机传送
     */
    @JvmStatic
    fun tp(p: Player, sendTitle: Boolean, world: World, x: Int, z: Int) {
        for (y in 150 downTo 11) {
            val loc = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
            val type = loc.block.type

            // 不传送到岩浆和水
            if (type in notBlock) {
                continue
            }

            val tYpe = loc.add(0.0, 1.0, 0.0).block.type
            if (tYpe in notBlock && tYpe != Material.AIR) {
                tp(p, sendTitle, world)
                return
            }
            p.teleport(loc.add(0.0, 1.0, 0.0))
            if (sendTitle) {
                p.sendTitle("§f传送完成", "§a你已被传送到 §7X$x Y$y Z$z", 20, 50, 20)
            }
            return
        }
        tp(p, sendTitle, world, x, z)
    }

    @JvmStatic
    fun tp(p: Player, sendTitle: Boolean, world: World) {
        tp(p, sendTitle, world, kotlin.random.Random.nextInt(8000) + 2000, kotlin.random.Random.nextInt(8000) + 2000)
    }

    /**
     * 随机传送
     */
    @JvmStatic
    fun tp(p: String, sendTitle: Boolean, world: String) {
        val player = Bukkit.getPlayer(p)
        val w = Bukkit.getWorld(world)
        if (player != null && w != null) {
            tp(player, sendTitle, w)
        }
    }

}