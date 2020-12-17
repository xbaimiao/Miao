package com.xbaimiao.miao;

import com.xbaimiao.miao.channel.ServerChannel;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BungeeMiao extends Plugin {

    public static BungeeMiao plugin;
    File configFile = new File(getDataFolder() + File.separator + "config.yml");
    public static Configuration config;
    ConfigurationProvider cProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);

    @Override
    public void onEnable() {
        plugin = this;
        try {
            if (!configFile.getParentFile().exists()){
                configFile.getParentFile().mkdirs();
            }
            if (!configFile.exists()){
                if (configFile.createNewFile()) {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8));
                    bw.write("port: 22223");
                    bw.close();
                }
            }
            config = cProvider.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ServerChannel(config.getInt("port")).start();
        getLogger().info("Miao load ing");
    }

}
