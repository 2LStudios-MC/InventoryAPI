package dev._2lstudios.inventoryapi;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.Inventory;

public class InventoryManager {
    private final Map<Inventory, InventoryWrapper> inventories = new HashMap<>();
    
    public void add(final Inventory key, final InventoryWrapper value) {
        inventories.put(key, value);
    }

    public void remove(final Inventory inventory) {
        inventories.remove(inventory);
    }

    public InventoryWrapper get(final Inventory inventory) {
        return inventories.getOrDefault(inventory, null);
    }
}
