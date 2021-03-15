package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import dev._2lstudios.inventoryapi.inventory.InventoryManager;
import dev._2lstudios.inventoryapi.inventory.InventoryWrapper;
import dev._2lstudios.inventoryapi.events.InventoryAPIOpenEvent;

public class InventoryOpenListener implements Listener {
    private final InventoryManager inventoryManager;

    public InventoryOpenListener(final InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInventoryOpen(final InventoryOpenEvent event) {
        final HumanEntity whoClicked = event.getPlayer();

        if (whoClicked instanceof Player) {
            final Player player = (Player) whoClicked;
            final InventoryWrapper inventoryWrapper = inventoryManager.get(player);

            if (inventoryWrapper != null) {
                final InventoryAPIOpenEvent event1 = new InventoryAPIOpenEvent(event, player, inventoryWrapper);

                Bukkit.getPluginManager().callEvent(event1);

                event.setCancelled(event1.isCancelled());
            }
        }
    }
}