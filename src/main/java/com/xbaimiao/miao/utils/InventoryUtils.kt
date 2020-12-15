package com.xbaimiao.miao.utils

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object InventoryUtils {

    /**
     * 以安全的方式 往玩家背包添加物品
     */
    fun Inventory.safeAddItem(loc: Location, item: ItemStack): Boolean {
        for (x in 0..35) {
            if (this.getItem(x) == null || this.getItem(x)!!.type == Material.AIR) {
                this.addItem(item)
                return true
            }
        }
        loc.world!!.dropItem(loc, item)
        return false
    }

    /**
     * 判断字符串是不是数字
     */
    @JvmStatic
    fun String.isNumeric(): Boolean {
        return this.matches("[0-9]*".toRegex())
    }

    /**
     * 获取该Inventory能容纳多少个该物品
     */
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

    /**
     * 获取该Inventory能容纳多少个该类型的物品(不判断名称)不推荐使用
     */
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