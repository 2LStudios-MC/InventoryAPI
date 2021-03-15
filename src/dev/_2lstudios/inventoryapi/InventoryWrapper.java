package dev._2lstudios.inventoryapi;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryWrapper {
    private final int page;
    private final String id;
    private final Inventory inventory;
    
    public InventoryWrapper(final int page, final String id, final Inventory inventory) {
        this.page = page;
        this.id = id;
        this.inventory = inventory;
    }

    public InventoryWrapper(final String id, final Inventory inventory) {
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
}
