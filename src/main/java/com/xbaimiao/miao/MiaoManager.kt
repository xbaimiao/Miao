package com.xbaimiao.miao

import com.xbaimiao.miao.command.CommandBuilder
import com.xbaimiao.miao.command.CommandHandle
import com.xbaimiao.miao.command.MainCommand
import org.bukkit.plugin.java.JavaPlugin

class MiaoManager {

    fun registerCommand(cmd: CommandHandle, plugin: JavaPlugin) {
        val mainCommand: MainCommand? = cmd::class.java.getAnnotation(MainCommand::class.java)
        if (mainCommand == null) {
            Miao.instance.logger.warning("${cmd}未注解MainCommand")
            return
        }
        val permission = if (mainCommand.permission == "default") null else mainCommand.permission
        val usage = if (mainCommand.usage == "default") null else mainCommand.usage
        val aliases = if (mainCommand.aliases == "default") null else mainCommand.aliases

        CommandBuilder(mainCommand.name, plugin)
            .setExecutor(cmd)
            .setTabCompleter(cmd)
            .setUsage(usage)
            .setLabel(mainCommand.name)
            .setAliases(aliases)
            .setPermission(permission)
            .register()
    }

}
