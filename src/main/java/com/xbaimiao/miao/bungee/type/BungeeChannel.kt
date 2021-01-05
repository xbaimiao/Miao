package com.xbaimiao.miao.bungee.type

import com.xbaimiao.miao.bungee.channel.Channel
import com.xbaimiao.miao.bungee.channel.ChannelRead
import com.xbaimiao.miao.bungee.channel.ChannelType

class BungeeChannel(channel: Channel) : ChannelRead(channel) {

    /**
     * 获取在线总列表
     */
    fun getOnlinePlayers(): List<String>? {
        return try {
            getChannelRead()
                    .send(ChannelType.ALL_PLAYER_LIST)
                    .readUTF()
                    .listFromString()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 获取在线玩家数量
     */
    fun getOnlinePlayerSize(): Int {
        return try {
            getChannelRead()
                    .send(ChannelType.ALL_PLAYER)
                    .readUTF()
                    .toInt()
        } catch (e: Exception) {
            0
        }
    }

    /**
     * 获取所有服务器的名称
     */
    fun getServers(): List<String>? {
        return try {
            getChannelRead()
                    .send(ChannelType.ALL_SERVER)
                    .readUTF()
                    .listFromString()
        } catch (e: Exception) {
            null
        }
    }

}