package com.xbaimiao.miao.item

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

class Base64Head(base64: String) : ItemStack(Material.PLAYER_HEAD, 1) {

	init {
		val skullmate = (itemMeta as SkullMeta?)!!
		val profile = GameProfile(UUID.randomUUID(), null)
		profile.properties.put("textures", Property("textures", base64))
		val field = skullmate.javaClass.getDeclaredField("profile")
		field.isAccessible = true
		field[skullmate] = profile
		itemMeta = skullmate
	}

}