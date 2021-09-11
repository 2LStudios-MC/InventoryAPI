package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.inventoryapi.inventory.InventoryManager;
import dev._2lstudios.inventoryapi.inventory.InventoryWrapper;
import dev._2lstudios.inventoryapi.events.InventoryAPIClickEvent;

public class InventoryClickListener implements Listener {
    private final InventoryManager inventoryManager;

    public InventoryClickListener(final InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInventoryClick(final InventoryClickEvent event) {
        final HumanEntity whoClicked = event.getWhoClicked();

        if (whoClicked instanceof Player) {
            final Player player = (Player) whoClicked;
            final InventoryWrapper inventoryWrapper = inventoryManager.get(player);

            if (inventoryWrapper != null) {
                event.setCancelled(true);

                final InventoryAPIClickEvent event1 = new InventoryAPIClickEvent(event, player,
                        inventoryWrapper);

                Bukkit.getPluginManager().callEvent(event1);
            }
        }
    }
}
