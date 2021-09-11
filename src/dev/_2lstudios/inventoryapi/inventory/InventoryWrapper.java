package dev._2lstudios.inventoryapi.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryWrapper {
    private final Player holder;
    private final String id;
    private final Inventory inventory;
    // The inventory that came before this one
    private InventoryWrapper lastInventory;
    private int page;
    
    public InventoryWrapper(final Player holder, final int page, final String id, final Inventory inventory) {
        this.holder = holder;
        this.page = page;
        this.id = id;
        this.inventory = inventory;
    }

    public InventoryWrapper(final Player holder, final String id, final Inventory inventory) {
        this.holder = holder;
        this.page = 0;
        this.id = id;
        this.inventory = inventory;
    }

    public int getPage() {
        return page;
    }

    public String getId() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setItem(final int index, final ItemStack item) {
        inventory.setItem(index, item);
    }

    public void setLastInventory(InventoryWrapper lastInventory) {
        this.lastInventory = lastInventory;
    }

    public InventoryWrapper getLastInventory() {
        return lastInventory;
    }

    public void openInventory() {
        if (!inventory.equals(holder.getOpenInventory().getTopInventory())) {
            holder.closeInventory();
            holder.openInventory(inventory);
        }
    }

    public void setPage(final int page) {
        this.page = page;
    }
}
