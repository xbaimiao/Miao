package com.xbaimiao.miao.bungee.type

import com.xbaimiao.miao.bungee.channel.Channel
import com.xbaimiao.miao.bungee.channel.ChannelRead

class TitleChannel(channel: Channel, playerName: String, title: String, subTitle: String) : ChannelRead(channel) {

    val isSuccess = writeUTF(playerName)
            .writeUTF(title)
            .writeUTF(subTitle)
            .readUTF() == "true"

}