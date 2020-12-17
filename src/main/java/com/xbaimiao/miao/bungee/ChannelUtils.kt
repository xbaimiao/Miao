package com.xbaimiao.miao.bungee

object ChannelUtils {

    /**
     * String 转为 list
     */
    @JvmStatic
    fun listFromString(string: String):List<String>{
        val list = string.substring(1,string.length - 1)
        return ArrayList(list.split(","))
    }

}