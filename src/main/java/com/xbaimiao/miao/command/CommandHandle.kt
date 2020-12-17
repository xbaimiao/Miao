package com.xbaimiao.miao.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

interface CommandHandle : CommandExecutor, TabExecutor {

    fun onCommand(sender: CommandSender, args: Array<out String>): Boolean

    fun onTabComplete(sender: CommandSender, args: Array<out String>): MutableList<String>?

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        return onCommand(p0, p3)
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>
    ): MutableList<String>? {
        return onTabComplete(p0, p3)
    }
}