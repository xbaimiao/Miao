package com.xbaimiao.miao

import net.md_5.bungee.config.ConfigurationProvider
import com.xbaimiao.miao.channel.ServerChannel
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.YamlConfiguration
import java.io.*
import java.nio.charset.StandardCharsets

class BungeeMiao : Plugin() {

    var configFile = File(dataFolder.toString() + File.separator + "config.yml")
    var cProvider = ConfigurationProvider.getProvider(YamlConfiguration::class.java)

    override fun onEnable() {
        plugin = this
        try {
            if (!configFile.parentFile.exists()) {
                configFile.parentFile.mkdirs()
            }
            if (!configFile.exists()) {
                if (configFile.createNewFile()) {
                    val bw = BufferedWriter(OutputStreamWriter(FileOutputStream(configFile), StandardCharsets.UTF_8))
                    bw.write("port: 22223")
                    bw.close()
                }
            }
            config = cProvider.load(configFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        ServerChannel(config!!.getInt("port")).start()
        logger.info("Miao load ing")
    }

    companion object {
        lateinit var plugin: BungeeMiao
        var config: Configuration? = null
    }

}