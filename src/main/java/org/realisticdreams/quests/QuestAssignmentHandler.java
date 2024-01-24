package org.realisticdreams.quests;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.realisticdreams.RealisticDreams;
import org.realisticdreams.utility.TF;

import java.util.*;

public class QuestAssignmentHandler {
    private final RealisticDreams plugin;
    String path = "Quests.Adventure.";

    public QuestAssignmentHandler(RealisticDreams plugin) {
        this.plugin = plugin;
    }

    public void failQuest(Player player) {
        UUID playerId = player.getUniqueId();
        if (QuestManager.hasActiveQuest(playerId)) {
            QuestManager.removeQuest(playerId);
            player.sendMessage(TF.format("Your quest has failed as you went to sleep.", TF.RED, true));
        }
    }

    public void assignAdventureQuest(Player player) {
        ConfigurationSection randomQuestSection = getRandomAdventureQuest();
        if (randomQuestSection == null) {
            player.sendMessage("No quest available.");
            return;
        }

        // Extracting the values from the ConfigurationSection
        String questName = randomQuestSection.getString("Name");
        String questDescription = randomQuestSection.getString("Description");
        String materialName = randomQuestSection.getString("Material");
        int quantity = randomQuestSection.getInt("Amount");
        int expReward = randomQuestSection.getInt("EXP-Reward");

        if (questName == null || materialName == null || quantity == 0 || questDescription == null || expReward == 0) {
            plugin.getLogger().warning("Invalid quest");
            return;
        }

        // Ensure that the Material is valid
        Material item = Material.matchMaterial(materialName);
        if (item == null) {
            plugin.getLogger().warning("Invalid material for quest: " + materialName);
            return;
        }

        // Create a new Quest object
        Quest adventureQuest = new Quest(
                questName,
                questDescription,
                player.getUniqueId(),
                item,
                quantity,
                expReward
        );

        // Assign the quest
        QuestManager.assignQuest(player.getUniqueId(), adventureQuest);

        // Inform the player
        player.sendMessage(TF.format("Your dream activated: " + questName + " (" + expReward + "xp)", TF.DARK_PURPLE, true));
        player.sendMessage(TF.format("Your goal: " + questDescription, TF.GREEN, true));
        player.sendMessage(TF.format("Finish the quest by using the '/checkquest' or '/cq' command." , TF.GREEN));
    }

    public ConfigurationSection getRandomAdventureQuest() {
        ConfigurationSection questSection = plugin.getConfig().getConfigurationSection(path);
        if (questSection == null) {
            return null;
        }

        Set<String> quests = questSection.getKeys(false);
        if (quests.isEmpty()) {
            return null;
        }

        int random = new Random().nextInt(quests.size());
        String randomQuestKey = (String) quests.toArray()[random];
        return questSection.getConfigurationSection(randomQuestKey);
    }
}
