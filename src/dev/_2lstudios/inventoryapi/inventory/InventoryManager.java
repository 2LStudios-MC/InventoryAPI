package dev._2lstudios.inventoryapi.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class InventoryManager {
    private final Map<Player, InventoryWrapper> inventories = new HashMap<>();
    
    public void put(final Player key, final InventoryWrapper value) {
        inventories.put(key, value);
    }

    public void remove(final Player key) {
        inventories.remove(key);
    }

    public InventoryWrapper get(final Player key) {
        return inventories.getOrDefault(key, null);
    }
}
