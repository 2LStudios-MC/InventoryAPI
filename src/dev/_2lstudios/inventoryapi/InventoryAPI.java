package dev._2lstudios.inventoryapi;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.inventoryapi.listeners.InventoryClickListener;
import dev._2lstudios.inventoryapi.listeners.InventoryCloseListener;
import dev._2lstudios.inventoryapi.listeners.InventoryOpenListener;
import dev._2lstudios.inventoryapi.listeners.PlayerJoinListener;
import dev._2lstudios.inventoryapi.listeners.PlayerQuitListener;

public class InventoryAPI extends JavaPlugin {
    private static InventoryAPI instance;
    private InventoryManager inventoryManager = null;
    private InventoryPlayerManager inventoryPlayerManager = null;
    private InventoryUtil inventoryUtil = null;

    public static void setInstance(final InventoryAPI instance) {
        InventoryAPI.instance = instance;
    }

    public static InventoryAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);

        final Server server = getServer();
        final PluginManager pluginManager = server.getPluginManager();

        inventoryManager = new InventoryManager();
        inventoryPlayerManager = new InventoryPlayerManager();
        inventoryUtil = new InventoryUtil(inventoryManager, server);

        for (final Player player : getServer().getOnlinePlayers()) {
            inventoryPlayerManager.add(player);
        }

        pluginManager.registerEvents(new InventoryClickListener(inventoryPlayerManager, inventoryManager), this);
        pluginManager.registerEvents(new InventoryCloseListener(inventoryPlayerManager, inventoryManager), this);
        pluginManager.registerEvents(new InventoryOpenListener(inventoryPlayerManager, inventoryManager), this);
        pluginManager.registerEvents(new PlayerJoinListener(inventoryPlayerManager), this);
        pluginManager.registerEvents(new PlayerQuitListener(inventoryPlayerManager), this);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public InventoryPlayerManager getInventoryPlayerManager() {
        return inventoryPlayerManager;
    }

    public InventoryUtil getInventoryUtil() {
        return inventoryUtil;
    }
}
