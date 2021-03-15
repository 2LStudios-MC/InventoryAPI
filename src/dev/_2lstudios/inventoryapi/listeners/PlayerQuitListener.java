package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev._2lstudios.inventoryapi.inventory.InventoryManager;

public class PlayerQuitListener implements Listener {
    private final InventoryManager inventoryManager;

    public PlayerQuitListener(final InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        inventoryManager.remove(event.getPlayer());
    }
}
