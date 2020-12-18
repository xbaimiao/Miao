package com.xbaimiao.miao.bungee.type

import com.xbaimiao.miao.bungee.channel.Channel
import com.xbaimiao.miao.bungee.channel.ChannelRead

class MessageChannel(channel: Channel, name: String, msg: String) : ChannelRead(channel) {

    val isSuccess = writeUTF(name).writeUTF(msg.replace(" ", "")).readUTF() == "true"

}