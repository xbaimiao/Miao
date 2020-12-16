package com.xbaimiao.miao.command

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
/**
 * sender: CommandSender, args: Array<out String>
 */
annotation class MainCommand(
        val name: String,
        val usage: String = "default",
        val permission: String = "default",
        val aliases: String = "default"
)