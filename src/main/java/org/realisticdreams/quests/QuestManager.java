package org.realisticdreams.quests;

import java.util.HashMap;
import java.util.UUID;

public class QuestManager {
    private static HashMap<UUID, Quest> activeQuests = new HashMap<>();

    public static void assignQuest(UUID playerUuid, Quest quest) {
        activeQuests.put(playerUuid, quest);
    }

    public static Quest getQuest(UUID playerUuid) {
        return activeQuests.get(playerUuid);
    }

    public static void removeQuest(UUID playerUuid) {
        activeQuests.remove(playerUuid);
    }

    public static Quest getActiveQuest(UUID uniqueId) {
        return activeQuests.get(uniqueId);
    }

    public static boolean hasActiveQuest(UUID playerId) {
        return activeQuests.containsKey(playerId);
    }
}
