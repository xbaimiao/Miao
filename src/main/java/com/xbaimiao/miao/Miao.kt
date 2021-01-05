package com.xbaimiao.miao

import com.xbaimiao.miao.event.t.PlayerRunnable
import net.milkbowl.vault.economy.Economy
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Miao : JavaPlugin(), Listener, CommandExecutor {

    override fun onEnable() {
        instance = this
        setupEconomy()
        PlayerRunnable().runTaskTimerAsynchronously(this, 20, 20)
    }

    private fun setupEconomy() {
        val economyProvider = server.servicesManager.getRegistration(
                Economy::class.java
        )
        if (economyProvider != null) {
            vaultAPI = economyProvider.provider
            logger.info("vault load success")
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        println("success")
        return true
    }

    companion object {
        val miaoManager = MiaoManager()
        lateinit var instance: Miao
        val menuPrefix = "◎"
        var prefix = "§7§l[§e§l!§7§l] §7"
        var vaultAPI: Economy? = null
    }
}