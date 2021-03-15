package dev._2lstudios.inventoryapi;

import org.bukkit.entity.Player;

public class InventoryPlayer {
    private final Player player;
    private InventoryWrapper inventory;

    public InventoryPlayer(final Player player) {
        this.player = player;
    }

    public void openInventory(final InventoryWrapper inventory) {
        this.inventory = inventory;
        player.openInventory(inventory.getInventory());
    }

    public void closeInventory() {
        inventory = null;
        player.closeInventory();
    }

    public Player getPlayer() {
        return player;
    }

    public InventoryWrapper getInventory() {
        return inventory;
    }

    public void setInventory(InventoryWrapper inventory) {
        this.inventory = inventory;
    }
}
