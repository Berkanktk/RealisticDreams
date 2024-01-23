package org.realisticdreams;

import org.bukkit.entity.Player;

import java.util.*;

public class SleepTracker {
    private HashMap<UUID, Long> lastSleepTimes = new HashMap<>();
    private final RealisticDreams plugin;
    String path = "Sleep Tracker";

    public SleepTracker(RealisticDreams plugin) {
        this.plugin = plugin;
    }

    public void getLastSleep(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        String trackerSetting = plugin.getConfig().getString(path);

        if (lastSleepTimes.containsKey(playerId) && Objects.equals(trackerSetting, "true")) {
            long lastSleepTime = lastSleepTimes.get(playerId);
            long timeSinceLastSleep = currentTime - lastSleepTime;
            long timeInMinutes = timeSinceLastSleep / 1000 / 60;

            player.sendMessage("§eYou last slept " + timeInMinutes + " minutes ago.");
        } else {
            player.sendMessage("§eThis is your first sleep in this session!");
        }

        lastSleepTimes.put(playerId, currentTime);
    }
}
