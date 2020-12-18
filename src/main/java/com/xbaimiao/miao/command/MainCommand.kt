package com.xbaimiao.miao.command

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
/**
 * 命令注解
 */
annotation class MainCommand(
    val name: String,
    val usage: String = "default",
    val permission: String = "default",
    val aliases: String = "default"
)