package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev._2lstudios.inventoryapi.inventory.InventoryManager;
import dev._2lstudios.inventoryapi.inventory.InventoryWrapper;
import dev._2lstudios.inventoryapi.events.InventoryAPICloseEvent;

public class InventoryCloseListener implements Listener {
    private final InventoryManager inventoryManager;

    public InventoryCloseListener(final InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInventoryClose(final InventoryCloseEvent event) {
        final HumanEntity whoClicked = event.getPlayer();

        if (whoClicked instanceof Player) {
            final Player player = (Player) whoClicked;
            final InventoryWrapper inventoryWrapper = inventoryManager.get(player);

            if (inventoryWrapper != null) {
                final InventoryAPICloseEvent event1 = new InventoryAPICloseEvent(event, player, inventoryWrapper);

                Bukkit.getPluginManager().callEvent(event1);

                if (event1.isCancelled()) {
                    player.openInventory(inventoryWrapper.getInventory());
                } else {
                    inventoryManager.remove(player);
                }
            }
        }
    }
}