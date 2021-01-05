package com.xbaimiao.miao.event

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @Auther: 小白
 * @Date: 2021/1/2 09:33
 * @Description:
 */
class PlayerInWater(private val player: Player) : Event(), Cancellable {

    fun getLocation():Location = player.location

    fun getPlayer():Player = player

    private var isCancellable = false

    override fun isCancelled(): Boolean {
        return isCancellable
    }

    override fun setCancelled(p0: Boolean) {
        isCancellable = p0
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        private val handlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList? {
            return handlerList
        }

    }

}