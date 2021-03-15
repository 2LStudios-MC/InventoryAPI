package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev._2lstudios.inventoryapi.InventoryPlayerManager;

public class PlayerQuitListener implements Listener {
    private final InventoryPlayerManager inventoryPlayerManager;

    public PlayerQuitListener(final InventoryPlayerManager inventoryPlayerManager) {
        this.inventoryPlayerManager = inventoryPlayerManager;
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        inventoryPlayerManager.remove(event.getPlayer());
    }
}
