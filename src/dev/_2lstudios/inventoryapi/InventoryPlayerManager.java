package dev._2lstudios.inventoryapi;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class InventoryPlayerManager {
    private final Map<UUID, InventoryPlayer> inventoryPlayers = new HashMap<>();

    public InventoryPlayer add(final Player player) {
        final InventoryPlayer inventoryPlayer = new InventoryPlayer(player);

        inventoryPlayers.put(player.getUniqueId(), inventoryPlayer);

        return inventoryPlayer;
    }

    public InventoryPlayer get(final Player player) {
        return inventoryPlayers.getOrDefault(player.getUniqueId(), null);
    }

    public void remove(final Player player) {
        inventoryPlayers.remove(player.getUniqueId());
    }
}
