package com.xbaimiao.miao.utils

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import kotlin.collections.ArrayList
import kotlin.random.Random

object Random {

	private val notBlock = ArrayList<Material>().also {
		it.add(Material.LAVA)
		it.add(Material.WATER)
		it.add(Material.AIR)
	}


	@JvmStatic
	fun tp(p: Player, sendTitle: Boolean, world: World) {
		val x: Int = Random.nextInt(8000) + 2000
		val z: Int = Random.nextInt(8000) + 2000
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
		tp(p, sendTitle, world)
	}

	@JvmStatic
	fun tp(p: String, sendTitle: Boolean, world: String) {
		val player = Bukkit.getPlayer(p)
		val w = Bukkit.getWorld(world)
		if (player != null && w != null) {
			tp(player, sendTitle, w)
		}
	}

}