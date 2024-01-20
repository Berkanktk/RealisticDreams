package org.realisticdreams.quests;

import org.bukkit.Material;

import java.util.UUID;

public class Quest {
    private String description;
    private UUID playerUuid;
    private Material objectiveItem;
    private int objectiveAmount;

    public Quest(String description, UUID uniqueId, Material material, int i) {
        this.description = description;
        this.playerUuid = uniqueId;
        this.objectiveItem = material;
        this.objectiveAmount = i;
    }

    public String getDescription() {
        return description;
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public Material getObjectiveItem() {
        return objectiveItem;
    }

    public int getObjectiveAmount() {
        return objectiveAmount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlayerUuid(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public void setObjectiveItem(Material objectiveItem) {
        this.objectiveItem = objectiveItem;
    }

    public void setObjectiveAmount(int objectiveAmount) {
        this.objectiveAmount = objectiveAmount;
    }
}
