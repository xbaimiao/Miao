package com.xbaimiao.miao;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Miao extends JavaPlugin {

    public static Miao instance;

    public static String prefix = "§7§l[§e§l!§7§l] §7";

    public static Economy vaultAPI = null;

    @Override
    public void onEnable() {
        instance = this;
        setupEconomy();
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            vaultAPI = economyProvider.getProvider();
            getLogger().info("加载成功");
        }
    }

}
