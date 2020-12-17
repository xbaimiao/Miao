package com.xbaimiao.miao.item

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta

class ItemBuilder(private val itemStack: ItemStack) {

    constructor(material: Material) : this(ItemStack(material))

    private val mate =
        if (itemStack.itemMeta != null)
            itemStack.itemMeta!!
        else Bukkit.getItemFactory().getItemMeta(itemStack.type)!!

    /**
     * 设置Lore
     */
    fun setLore(lore: List<String>): ItemBuilder {
        mate.lore = lore
        return this
    }

    /**
     * 以安全的方式添加附魔
     */
    fun addEnchantment(ench: Enchantment, level: Int): ItemBuilder {
        mate.addEnchant(ench, level, false)
        return this
    }

    /**
     * 以安全的方式批量添加附魔
     */
    fun addEnchantments(map: HashMap<Enchantment, Int>): ItemBuilder {
        map.forEach { (k, v) ->
            mate.addEnchant(k, v, false)
        }
        return this
    }

    /**
     * 以不安全的方式添加附魔
     */
    fun addUnsafeEnchantment(ench: Enchantment, level: Int): ItemBuilder {
        mate.addEnchant(ench, level, true)
        return this
    }

    /**
     * 以不安全的方式批量添加附魔
     */
    fun addUnsafeEnchantments(map: HashMap<Enchantment, Int>): ItemBuilder {
        map.forEach { (k, v) ->
            mate.addEnchant(k, v, true)
        }
        return this
    }

    /**
     * 移除指定的附魔
     */
    fun removeEnchantment(ench: Enchantment): ItemBuilder {
        itemStack.removeEnchantment(ench)
        return this
    }

    /**
     * 设置数量
     */
    fun setAmount(num: Int): ItemBuilder {
        itemStack.amount = num
        return this
    }

    /**
     * 设置物品类型
     */
    fun setType(material: Material): ItemBuilder {
        itemStack.type = material
        return this
    }

    /**
     * 设置物品耐久
     */
    fun setDurability(num: Int): ItemBuilder {
        if (mate is Damageable) {
            mate.damage = num
        }
        return this
    }

    /**
     * 设置物品源数据
     */
    fun setItemMeta(itemMeta: ItemMeta): ItemBuilder {
        itemStack.itemMeta = itemMeta
        return this
    }

    /**
     * 设置物品显示名称
     */
    fun setDisplayName(name: String): ItemBuilder {
        mate.setDisplayName(name)
        return this
    }

    /**
     * 设置本地化名称
     */
    fun setLocalizedName(name: String): ItemBuilder {
        mate.setLocalizedName(name)
        return this
    }

    /**
     * 设置材质数据
     */
    fun setCustomModelData(data: Int): ItemBuilder {
        mate.setCustomModelData(data)
        return this
    }

    /**
     * 添加物品标签
     */
    fun addItemFlag(flag: ItemFlag): ItemBuilder {
        mate.addItemFlags(flag)
        return this
    }

    /**
     * 设置无法破坏
     */
    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        mate.isUnbreakable = unbreakable
        return this
    }

    /**
     * 构建物品
     */
    fun build(): ItemStack {
        itemStack.itemMeta = mate
        return itemStack.clone()
    }


}