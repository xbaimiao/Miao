package com.xbaimiao.miao.utils

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object Pack {

	/**
	 * 判断字符串是不是数字
	 */
	@JvmStatic
	fun String.isNumeric(): Boolean {
		return this.matches("[0-9]*".toRegex())
	}

	/**
	 * 获取该背包能容纳多少个指定物品
	 */
	@Deprecated(message = "此方法只能判断指定物品的名字")
	@JvmStatic
	fun getVolumeName(inventory: Inventory, itemStack: ItemStack): Int {
		var a = 0
		for (x in 0..35) {
			val item = inventory.getItem(x)
			if (item == null) {
				a += 64
				continue
			}
			val itemMeta = item.itemMeta
			if (itemMeta != null) {
				if (itemMeta.hasDisplayName()) {
					if (item.type == itemStack.type) {
						if (item.amount >= 64) {
							continue
						}
						a += 64 - item.amount
					}
				}
			}
		}
		return a
	}

	@JvmStatic
	fun getVolume(inventory: Inventory, itemStack: ItemStack): Int {
		var a = 0
		for (x in 0..35) {
			val item = inventory.getItem(x)
			if (item == null) {
				a += 64
				continue
			}
			val itemMeta = item.itemMeta
			if (itemMeta != null) {
				if (item == itemStack) {
					if (item.amount >= 64) {
						continue
					}
					a += 64 - item.amount
				}
			}
		}
		return a
	}
}