package org.realisticdreams.quests;

import org.bukkit.Material;

import java.util.UUID;

public class Quest {
    private UUID playerUuid;
    private String name;
    private String description;
    private Material objectiveItem;
    private int objectiveAmount;
    private int expReward;

    public Quest(String name, String description, UUID uniqueId, Material material, int i, int expReward) {
        this.name = name;
        this.description = description;
        this.playerUuid = uniqueId;
        this.objectiveItem = material;
        this.objectiveAmount = i;
        this.expReward = expReward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int reward) {
        this.expReward = reward;
    }
}
