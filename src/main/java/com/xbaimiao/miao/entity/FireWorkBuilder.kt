package com.xbaimiao.miao.entity

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework

/**
 * @Auther: 小白
 * @Date: 2020/12/31 21:57
 * @Description:
 */
class FireWorkBuilder(location: Location) {

    val fireWork = location.world!!.spawnEntity(location, EntityType.FIREWORK) as Firework

    val fireWorks = fireWork.fireworkMeta

    val builder = FireworkEffect.builder()

    fun addEffect(effect: FireworkEffect) {
        fireWorks.addEffect(effect)
    }

    fun setColor(color: Color) {
        addEffect(builder.withColor(color).build())
    }

    fun setFade(color: Color) {
        addEffect(builder.withFade(color).build())
    }

    fun with(type: FireworkEffect.Type) {
        addEffect(builder.with(type).build())
    }

    fun setTime(int: Int) {
        fireWorks.power = int
    }

    fun build() {
        fireWork.fireworkMeta = fireWorks
    }

}