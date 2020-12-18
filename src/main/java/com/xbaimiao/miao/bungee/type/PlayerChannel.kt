package com.xbaimiao.miao.bungee.type

import com.google.gson.Gson
import com.xbaimiao.miao.bungee.channel.Channel
import com.xbaimiao.miao.bungee.channel.ChannelRead
import com.xbaimiao.miao.bungee.channel.ChannelType
import net.md_5.bungee.api.chat.TextComponent

class PlayerChannel(private val channel: Channel, val name: String) : ChannelRead(channel) {


    /**
     * 玩家IP地址
     */
    val ip = getChannelRead()
            .send(ChannelType.IP)
            .writeUTF(name)
            .readUTF()

    /**
     * 玩家uuid
     */
    val uuid = getChannelRead()
            .send(ChannelType.UUID)
            .writeUTF(name)
            .readUTF()

    /**
     * 给玩家发送消息
     */
    fun sendMessage(msg: String):Boolean{
        return getChannelRead()
                .send(ChannelType.MESSAGE)
                .writeUTF(name)
                .writeUTF(msg.replace(" ", ""))
                .readUTF() == "true"
    }

    /**
     * 给玩家发送消息
     */
    fun sendMessage(msg: TextComponent):Boolean{
        val gson = Gson()
        val msgString = gson.toJson(msg)
        return getChannelRead()
                .send(ChannelType.TEXT_COMPONENT)
                .writeUTF(name)
                .writeUTF(msgString.replace(" ", ""))
                .readUTF() == "true"
    }

    /**
     * 给玩家发送title
     */
    fun sendTitle(title: String,subTitle:String):Boolean{
        return getChannelRead()
                .send(ChannelType.TITLE)
                .writeUTF(name)
                .writeUTF(title)
                .writeUTF(subTitle)
                .readUTF() == "true"
    }


}