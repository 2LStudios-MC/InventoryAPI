package dev._2lstudios.inventoryapi;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.inventoryapi.inventory.InventoryManager;
import dev._2lstudios.inventoryapi.inventory.InventoryUtil;
import dev._2lstudios.inventoryapi.listeners.InventoryClickListener;
import dev._2lstudios.inventoryapi.listeners.InventoryCloseListener;
import dev._2lstudios.inventoryapi.listeners.InventoryOpenListener;
import dev._2lstudios.inventoryapi.listeners.PlayerQuitListener;

public class InventoryAPI extends JavaPlugin {
    private static InventoryAPI instance;
    private InventoryManager inventoryManager = null;
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
        inventoryUtil = new InventoryUtil(inventoryManager, server);

        pluginManager.registerEvents(new InventoryClickListener(inventoryManager), this);
        pluginManager.registerEvents(new InventoryCloseListener(inventoryManager), this);
        pluginManager.registerEvents(new InventoryOpenListener(inventoryManager), this);
        pluginManager.registerEvents(new PlayerQuitListener(inventoryManager), this);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public InventoryUtil getInventoryUtil() {
        return inventoryUtil;
    }
}
