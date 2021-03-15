package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.inventoryapi.InventoryManager;
import dev._2lstudios.inventoryapi.InventoryPlayer;
import dev._2lstudios.inventoryapi.InventoryPlayerManager;
import dev._2lstudios.inventoryapi.InventoryWrapper;
import dev._2lstudios.inventoryapi.events.InventoryAPIClickEvent;

public class InventoryClickListener implements Listener {
    private final InventoryPlayerManager inventoryPlayerManager;
    private final InventoryManager inventoryManager;

    public InventoryClickListener(final InventoryPlayerManager inventoryPlayerManager,
            final InventoryManager inventoryManager) {
        this.inventoryPlayerManager = inventoryPlayerManager;
        this.inventoryManager = inventoryManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onInventoryClick(final InventoryClickEvent event) {
        final InventoryWrapper inventoryWrapper = inventoryManager.get(event.getInventory());

        if (inventoryWrapper != null) {
            event.setCancelled(true);

            final HumanEntity whoClicked = event.getWhoClicked();

            if (whoClicked instanceof Player) {
                final Player player = (Player) whoClicked;
                final InventoryPlayer inventoryPlayer = inventoryPlayerManager.get(player);

                if (inventoryPlayer != null) {
                    final InventoryAPIClickEvent inventoryAPIClickEvent = new InventoryAPIClickEvent(event,
                            inventoryPlayer, inventoryWrapper);

                    Bukkit.getPluginManager().callEvent(inventoryAPIClickEvent);
                }
            }
        }
    }
}
