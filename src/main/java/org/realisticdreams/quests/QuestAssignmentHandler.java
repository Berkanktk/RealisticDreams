package org.realisticdreams.quests;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.realisticdreams.DreamManager;
import org.realisticdreams.utility.TF;

import java.util.*;

public class QuestAssignmentHandler {
    private Random random = new Random();

    private final List<Map.Entry<Material, Integer>> questItems = Arrays.asList(
            new AbstractMap.SimpleEntry<>(Material.IRON_INGOT, 10),
            new AbstractMap.SimpleEntry<>(Material.GOLD_INGOT, 5),
            new AbstractMap.SimpleEntry<>(Material.DIAMOND, 1),
            new AbstractMap.SimpleEntry<>(Material.COAL, 12),
            new AbstractMap.SimpleEntry<>(Material.OAK_LOG, 32),
            new AbstractMap.SimpleEntry<>(Material.BONE, 8),
            new AbstractMap.SimpleEntry<>(Material.COPPER_INGOT, 15)
    );

    public void failQuest(Player player) {
        UUID playerId = player.getUniqueId();
        if (QuestManager.hasActiveQuest(playerId)) {
            QuestManager.removeQuest(playerId);
            player.sendMessage(TF.format("Your quest has failed as you went to sleep.", TF.RED, true));
        }
    }

    public void assignAdventureQuest(Player player) {
        Map.Entry<Material, Integer> questItem = questItems.get(random.nextInt(questItems.size()));
        Material item = questItem.getKey();
        int quantity = questItem.getValue();

        Quest adventureQuest = new Quest("Collect " + item.name(), player.getUniqueId(), item, quantity);
        QuestManager.assignQuest(player.getUniqueId(), adventureQuest);
        player.sendMessage(TF.format("Your quest: Collect " + quantity + " " + item.name(), TF.GREEN, true));
    }
}
