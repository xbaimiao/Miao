package com.xbaimiao.miao.utils.protocolLib

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.PacketType.Play.Client.UPDATE_SIGN
import com.comphenix.protocol.PacketType.Play.Server.OPEN_SIGN_ENTITY
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.wrappers.BlockPosition
import com.comphenix.protocol.wrappers.WrappedBlockData
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.Material
import org.bukkit.entity.Player


class Send {

    var manager = ProtocolLibrary.getProtocolManager()!!

    fun Player.openSignGUI(lines: Array<String>) {
        val z = this.location.z.toInt()
        val y = this.location.y.toInt()
        val x = this.location.x.toInt()
        val blockChange = manager.createPacket(PacketType.Play.Server.BLOCK_CHANGE)
        blockChange.blockPositionModifier.write(0, BlockPosition(x, y, z))
        blockChange.blockData.write(0, WrappedBlockData.createData(Material.ACACIA_SIGN))

        val updateSign: PacketContainer = manager.createPacket(UPDATE_SIGN)
        updateSign.blockPositionModifier.write(0, BlockPosition(x, y, z))
        updateSign.chatComponentArrays.write(
            0,
            arrayOf(
                WrappedChatComponent.fromText(lines[0]),
                WrappedChatComponent.fromText(lines[1]),
                WrappedChatComponent.fromText(lines[2]),
                WrappedChatComponent.fromText(lines[3])
            )
        )

        val open = manager.createPacket(OPEN_SIGN_ENTITY)
        open.blockPositionModifier.write(0, BlockPosition(x, y, z))
        manager.sendServerPacket(player, blockChange)
    }

    /**
     * 这样我们就完成了打开牌子的UI

    监听关闭我们打开的牌子
    如果你是想通过牌子来修改某些东西，那我们就需要读取牌子修改后的内容，这时我们就需要通过监听包。
    一般的实现原理：
    如果你不想监听，也要把客户端发过来的属于你打开的牌子的 UPDATE_SIGN 取消掉，否则服务器会发现对应方块不是一个牌子
    玩家在完成修改任意一个牌子后，客户端会发送包 (UPDATE_SIGN) 给服务器，其中包含了牌子修改后的数据（ESC 或 完成 都将发送）
    客户端发送的 UPDATE_SIGN 包 (PlayInUpdateSign) 中，包含了牌子的坐标，我们可以通过牌子的坐标和玩家确定是否是我们创建的牌子

    开始监听
    在发送牌子后，存储玩家和坐标到一个 Map<Player, BlockPosition> map = new HashMap<>() ; //留意线程是否安全
    通过以下代码在 onEnable 中注册监听 UPDATE_SIGN
    <blockquote>manager.addPacketListener(this.packetListener = new PacketAdapter(plugin, new PacketType[]{PacketType.Play.Client.UPDATE_SIGN}) {
    复制代码
    以上代码纯手撸（这编辑器还吞格式，恶心），可能有写错，欢迎提出。

    总结
    这样你就使用 ProtocolLib 打开了 Sign GUI，本教程更希望你能掌握 ProtocolLib 的基本使用
     */
}