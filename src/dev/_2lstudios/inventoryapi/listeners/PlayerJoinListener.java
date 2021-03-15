package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.inventoryapi.InventoryPlayerManager;

public class PlayerJoinListener implements Listener {
    private final InventoryPlayerManager inventoryPlayerManager;

    public PlayerJoinListener(final InventoryPlayerManager inventoryPlayerManager) {
        this.inventoryPlayerManager = inventoryPlayerManager;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        inventoryPlayerManager.add(event.getPlayer());
    }
}
