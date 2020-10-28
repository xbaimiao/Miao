package com.xbaimiao.miao;

import org.bukkit.plugin.java.JavaPlugin;

public class Miao extends JavaPlugin {

    public static Miao instance;

    @Override
    public void onEnable() {
        instance = this;
    }

}
