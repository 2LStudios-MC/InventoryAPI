package dev._2lstudios.inventoryapi;

import java.util.Collection;
import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtil {
    private final InventoryManager inventoryManager;
    private final Server server;

    public InventoryUtil(final InventoryManager inventoryManager, final Server server) {
        this.inventoryManager = inventoryManager;
        this.server = server;
    }

    // This type of inventory displays 28 items
    // Commonly used for stores with multiple pages
    public InventoryWrapper createDisplayInventory(final String title, final InventoryHolder holder, int page,
            final String id, final Collection<ItemStack> items) {
        final Inventory inventory = server.createInventory(holder, 54, title);
        final InventoryWrapper inventoryWrapper = new InventoryWrapper(page, id, inventory);
        // First 10 slots are skipped
        int slot = 10;
        int itemCount = 0;

        for (final ItemStack item : items) {
            if (slot < 44) { // Slot 43 is the maximum we can populate
                inventory.setItem(slot, item);
                itemCount++;

                // We skip 3 slots when the row is filled
                if (itemCount % 7 == 0) {
                    slot += 3;
                } else {
                    slot++;
                }
            }
        }

        inventoryManager.add(inventory, inventoryWrapper);

        return inventoryWrapper;
    }

    // This is just an empty inventory
    public InventoryWrapper createInventory(final String title, final InventoryHolder holder, int page,
            final String id) {
        final Inventory inventory = server.createInventory(holder, 54, title);
        final InventoryWrapper inventoryWrapper = new InventoryWrapper(page, id, inventory);

        inventoryManager.add(inventory, inventoryWrapper);

        return inventoryWrapper;
    }

    public ItemStack getCloseItem() {
        final ItemStack item = new ItemStack(Material.ARROW);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Volver");
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getBackItem(final int page) {
        final ItemStack item = new ItemStack(Material.ARROW);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Pagina Anterior");
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Pagina " + page));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getNextItem(final int page) {
        final ItemStack item = new ItemStack(Material.ARROW);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Pagina Posterior");
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Pagina " + page));
        item.setItemMeta(meta);

        return item;
    }
}
