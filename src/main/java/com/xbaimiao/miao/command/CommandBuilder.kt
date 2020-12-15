package com.xbaimiao.miao.command

import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandMap
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.SimplePluginManager

class CommandBuilder(val commandName: String, val plugin: Plugin) {

    val constructor by lazy {
        val pluginCommand = PluginCommand::class.java.getDeclaredConstructor(String::class.java, Plugin::class.java)
        pluginCommand.isAccessible = true
        pluginCommand
    }

    val commandMap by lazy {
        val field = SimplePluginManager::class.java.getDeclaredField("commandMap")
        field.isAccessible = true
        val commandMap = field.get(plugin.server.pluginManager) as CommandMap
        commandMap
    }

    var command: PluginCommand

    init {
        command = constructor.newInstance(commandName, plugin)
    }

    /**
     * 设置别名
     */
    fun setAliases(list: List<String>): CommandBuilder {
        command.aliases = list
        return this
    }

    /**
     * 设置别名
     */
    fun setAliases(name: String?): CommandBuilder {
        name ?: return this
        command.aliases = ArrayList<String>().also { it.add(name) }
        return this
    }

    /**
     * 设置介绍
     */
    fun setDescription(string: String?): CommandBuilder {
        string ?: return this
        command.description = string
        return this
    }

    /**
     * 设置用法
     */
    fun setUsage(string: String?): CommandBuilder {
        string ?: return this
        command.usage = string
        return this
    }

    /**
     * 设置权限
     */
    fun setPermission(permission: String?): CommandBuilder {
        permission ?: return this
        command.permission = permission
        return this
    }

    /**
     * 设置TAB处理器
     */
    fun setTabCompleter(tabCompleter: TabCompleter?): CommandBuilder {
        command.setTabCompleter(tabCompleter)
        return this
    }

    /**
     * 设置命令处理器
     */
    fun setExecutor(commandExecutor: CommandExecutor): CommandBuilder {
        command.setExecutor(commandExecutor)
        return this
    }

    fun register() {
        commandMap.register(commandName, command)
    }

}