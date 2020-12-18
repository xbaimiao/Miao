package com.xbaimiao.miao.bungee.channel

enum class ChannelType {
    /**
     * 所有玩家
     */
    ALL_PLAYER,

    /**
     * 所有服务器的玩家列表
     */
    ALL_PLAYER_LIST,

    /**
     * 所有服务器的名称
     */
    ALL_SERVER,

    /**
     * 服务器信息
     * writeUTF ServerName
     */
    SERVER,

    /**
     * 玩家ip
     * writeUTF playerName
     */
    IP,

    /**
     * 指定服务器玩家数量
     * writeUTF ServerName
     */
    PLAYER_COUNT,

    /**
     * 服务器玩家列表
     * writeUTF ServerName
     */
    PLAYER_LIST,

    /**
     * 发送消息
     * writeUTF Name
     * writeUTF Message
     */
    MESSAGE,

    /**
     * 获取玩家UUID
     * wtiteUTF PlayerName
     */
    UUID,

    /**
     * 发送title
     */
    TITLE,
}