package com.xbaimiao.miao.utils.economics

import com.xbaimiao.miao.Miao
import org.bukkit.OfflinePlayer

object Vault {

    /**
     * 获取玩家的钱
     */
    @JvmStatic
    fun OfflinePlayer.getMoney():Double{
        return Miao.vaultAPI.getBalance(this)
    }

    /**
     * 给与玩家钱
     */
    @JvmStatic
    fun OfflinePlayer.giveMoney(vault:Double){
        Miao.vaultAPI.depositPlayer(this,vault)
    }

    /**
     * 扣除玩家钱
     */
    @JvmStatic
    fun OfflinePlayer.takeMoney(vault: Double){
        Miao.vaultAPI.withdrawPlayer(this,vault)
    }

    /**
     * 玩家是否有那么多钱
     */
    @JvmStatic
    fun OfflinePlayer.hasMoney(vault: Double):Boolean{
        return this.getMoney() >= vault
    }


}