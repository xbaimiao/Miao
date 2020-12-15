package com.xbaimiao.miao;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Miao extends JavaPlugin implements Listener, CommandExecutor {

    public static Miao instance;

    public static String prefix = "§7§l[§e§l!§7§l] §7";

    public static Economy vaultAPI = null;

    private static MiaoManager miaoManager;

    public static MiaoManager getMiaoManager() {
        return miaoManager;
    }

    public static Miao getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        setupEconomy();
        miaoManager = new MiaoManager();
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            vaultAPI = economyProvider.getProvider();
            getLogger().info("vault load success");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        System.out.println("success");
        return true;
    }
}
