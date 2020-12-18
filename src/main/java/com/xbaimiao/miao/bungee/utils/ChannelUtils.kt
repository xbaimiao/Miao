package com.xbaimiao.miao.bungee.utils

object ChannelUtils {

    /**
     * String 转为 list
     */
    @JvmStatic
    fun String.listFromString(): List<String> {
        val list = this.substring(1, this.length - 1).replace(" ", "")
        return ArrayList(list.split(","))
    }

}