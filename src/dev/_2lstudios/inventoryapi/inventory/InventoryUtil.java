package dev._2lstudios.inventoryapi.inventory;

import java.util.Collection;
import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtil {
    private final InventoryManager inventoryManager;
    private final Server server;

    public InventoryUtil(final InventoryManager inventoryManager, final Server server) {
        this.inventoryManager = inventoryManager;
        this.server = server;
    }

    Inventory getOrCreate(final Player holder, final int size, final String title) {
        final InventoryWrapper inventoryWrapper = inventoryManager.get(holder);

        if (inventoryWrapper != null) {
            final Inventory inventory = inventoryWrapper.getInventory();

            if (inventory != null && title.equals(inventory.getTitle()) && inventory.getSize() == size) {
                inventory.clear();

                return inventory;
            }
        }

        return server.createInventory(holder, size, title);
    }

    // This type of inventory displays 28 items
    // Commonly used for stores with multiple pages
    public InventoryWrapper createDisplayInventory(final String title, final Player holder, int page, final String id,
            final Collection<ItemStack> items) {
        final Inventory inventory = getOrCreate(holder, 54, title);
        final InventoryWrapper inventoryWrapper = new InventoryWrapper(page, id, inventory);
        final int lastPage = 1 + ((items.size() - 1) / 28);
        int skip = 28 * (page - 1);
        // First 10 slots are skipped
        int slot = 10;
        int itemCount = 0;

        for (final ItemStack item : items) {
            if (skip-- > 0) {
                continue;
            }

            if (slot < 44) { // Slot 43 is the maximum we can populate
                inventory.setItem(slot++, item);
                itemCount++;

                // We skip 3 slots when the row is filled
                if (itemCount % 7 == 0) {
                    slot += 2;
                }
            }
        }

        if (page != 1) {
            inventoryWrapper.setItem(45, getBackItem(page));
        }

        inventoryWrapper.setItem(49, getCloseItem());

        if (page != lastPage) {
            inventoryWrapper.setItem(53, getNextItem(page));
        }

        if (holder.getOpenInventory() != inventory) {
            holder.closeInventory();
            holder.openInventory(inventory);
        }

        inventoryManager.put(holder, inventoryWrapper);

        return inventoryWrapper;
    }

    // This is just an empty inventory
    public InventoryWrapper createInventory(final String title, final Player holder, int page, final String id) {
        final Inventory inventory = getOrCreate(holder, 54, title);
        final InventoryWrapper inventoryWrapper = new InventoryWrapper(page, id, inventory);

        if (holder.getOpenInventory() != inventory) {
            holder.closeInventory();
            holder.openInventory(inventory);
        }

        inventoryManager.put(holder, inventoryWrapper);

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
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Pagina " + (page - 1)));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getNextItem(final int page) {
        final ItemStack item = new ItemStack(Material.ARROW);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Pagina Posterior");
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Pagina " + (page + 1)));
        item.setItemMeta(meta);

        return item;
    }
}
