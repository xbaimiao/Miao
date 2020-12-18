package com.xbaimiao.miao.bungee.type

import com.xbaimiao.miao.bungee.channel.Channel
import com.xbaimiao.miao.bungee.channel.ChannelRead
import com.xbaimiao.miao.bungee.channel.ChannelType

class ServerChannel(val channel: Channel, val serverName: String) : ChannelRead(channel) {

    /**
     * 获取服务器在线玩家列表
     */
    fun getPlayerList(): List<String>? {
        return try {
            getChannelRead()
                    .send(ChannelType.PLAYER_LIST)
                    .writeUTF(serverName)
                    .readUTF().listFromString()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 获取服务器在线玩家数量
     */
    fun getPlayerCount(): Int? {
        return try {
            getChannelRead()
                    .send(ChannelType.PLAYER_LIST)
                    .writeUTF(serverName)
                    .readUTF()
                    .toInt()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 获取服务器信息
     */
    fun getServerInfo(): String? {
        return try {
            getChannelRead()
                    .send(ChannelType.SERVER)
                    .writeUTF(serverName)
                    .readUTF()
        } catch (e: Exception) {
            null
        }
    }

}