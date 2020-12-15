package com.xbaimiao.miao.utils

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @JvmStatic
    fun stampToDate(s: Long): String {
        val date = Date(s)
        return simpleDateFormat.format(date)
    }

    @JvmStatic
    fun dateToStamp(s: String): Long {
        val date = simpleDateFormat.parse(s)
        val ts = date.time
        return ts.toString().toLong()
    }

    @JvmStatic
    fun getCurrentTime(): String {
        return stampToDate(System.currentTimeMillis())
    }

    class FileNotFind(s: String) : Exception(s)

    @JvmStatic
    fun copyFile(file1: File, file2: File) {
        if (file1.exists()) {
            val input = FileInputStream(file1)
            val out = FileOutputStream(file2)
            val buff = ByteArray(1024)
            var len: Int?
            while (input.read(buff).also { len = it } > 0) {
                out.write(buff, 0, len!!)
            }
            input.close()
            out.flush()
            out.close()
            return
        }
        if (file2.exists()) {
            copyFile(file2, file1)
            return
        }
        try {
            throw FileNotFind("文件都不给我,我怎么复制")
        } catch (e: FileNotFind) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun moveFile(file: File, path: String, isCover: Boolean) {
        moveFile(file, File(path), isCover)
    }

    @JvmStatic
    fun moveFile(file: File, path: String) {
        moveFile(file, File(path), true)
    }

    @JvmStatic
    fun moveFile(file: File, file1: File) {
        moveFile(file, file1, true)
    }

    @JvmStatic
    fun moveFile(file: File, file1: File, isCover: Boolean) {
        if (!isCover) {
            if (file1.exists()) {
                return
            }
        }
        if (file.exists()) {
            val input = FileInputStream(file)
            val out = FileOutputStream(file1)
            val buff = ByteArray(1024)
            var len: Int?
            while (input.read(buff).also { len = it } > 0) {
                out.write(buff, 0, len!!)
            }
            out.flush()
            out.close()
            input.close()
            file.delete()
            return
        }
        try {
            throw FileNotFind("文件未找到,你让我怎么移动")
        } catch (e: FileNotFind) {
            e.printStackTrace()
        }
    }

    /**
     * 以安全的方式 往玩家背包添加物品
     */
    fun Inventory.safeAddItem(loc: Location, item: ItemStack): Boolean {
        for (x in 0..35) {
            if (this.getItem(x) == null || this.getItem(x)!!.type == Material.AIR) {
                this.addItem(item)
                return true
            }
        }
        loc.world!!.dropItem(loc, item)
        return false
    }

}