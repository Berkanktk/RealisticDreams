package org.realisticdreams;

import org.bukkit.entity.Player;

import java.util.*;

public class SleepTracker {
    private HashMap<UUID, Long> lastSleepTimes = new HashMap<>();

    public void getLastSleep(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (lastSleepTimes.containsKey(playerId)) {
            long lastSleepTime = lastSleepTimes.get(playerId);
            long timeSinceLastSleep = currentTime - lastSleepTime;
            long timeInMinutes = timeSinceLastSleep / 1000 / 60;

            player.sendMessage("§eYou last slept " + timeInMinutes + " minutes ago.");
        } else {
            player.sendMessage("§eThis is your first sleep in the game!");
        }

        lastSleepTimes.put(playerId, currentTime);
    }
}
