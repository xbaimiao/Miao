package com.xbaimiao.miao.utils

import com.xbaimiao.miao.Miao
import com.xbaimiao.miao.command.CommandHandle
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.JarFile
import kotlin.collections.ArrayList

object Utils {

    /**
     * plugins的全部插件
     */
    val jarFiles by lazy {
        val filePlugins = File("${System.getProperty("user.dir")}${File.separator}plugins")
        val list = ArrayList<JarFile>()
        if (filePlugins.listFiles() != null) {
            for (file in filePlugins.listFiles()!!) {
                if (!file.isDirectory) {
                    if (file.name.endsWith(".jar")) {
                        list.add(JarFile(file))
                    }
                }
            }
        }
        list
    }


    /**
     * 获取插件的文件名字
     */
    @JvmStatic
    fun getPluginFileName(name: String): String? {
        for (jarFile in jarFiles) {
            val pluginYml = jarFile.getEntry("plugin.yml") ?: continue
            val br = BufferedReader(InputStreamReader(jarFile.getInputStream(pluginYml), StandardCharsets.UTF_8))
            var s: String?
            while (br.readLine().also { s = it } != null) {
                if (s != null) {
                    s = s!!.replace(" ", "")
                    if (s!!.startsWith("name")) {
                        if (s!!.split(":")[1] == name) {
                            return jarFile.name
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * 获取插件对应的文件
     */
    @JvmStatic
    fun getPluginFile(name: String): File? {
        val a = getPluginFileName(name) ?: return null
        return File(a)
    }

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    /**
     * 时间戳转时间
     */
    @JvmStatic
    fun stampToDate(s: Long): String {
        val date = Date(s)
        return simpleDateFormat.format(date)
    }

    /**
     * 时间转时间戳
     */
    @JvmStatic
    fun dateToStamp(s: String): Long {
        val date = simpleDateFormat.parse(s)
        val ts = date.time
        return ts.toString().toLong()
    }

    /**
     * 当前时间s
     */
    @JvmStatic
    fun getCurrentTime(): String {
        return stampToDate(System.currentTimeMillis())
    }

    @JvmStatic
    fun Listener.registerListener(plugin: JavaPlugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @JvmStatic
    fun CommandHandle.registerCommand(plugin: JavaPlugin) {
        Miao.miaoManager.registerCommand(this, plugin)
    }

}