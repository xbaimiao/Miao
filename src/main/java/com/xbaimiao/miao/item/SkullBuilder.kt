package com.xbaimiao.miao.item

import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

/**
 * @Auther: 小白
 * @Date: 2020/12/28 14:09
 * @Description:
 */
class SkullBuilder : ItemBuilder(Material.PLAYER_HEAD) {

    private val skullMeta = mate as SkullMeta

    fun setOwnerPlayer(player: OfflinePlayer): SkullBuilder {
        skullMeta.owningPlayer = player
        return this
    }

    override fun build(): ItemStack {
        itemStack.itemMeta = skullMeta
        return itemStack
    }

}