package org.realisticdreams.sleep;

import org.bukkit.entity.Player;
import org.realisticdreams.RealisticDreams;
import org.realisticdreams.utility.TF;

import java.util.*;

public class SleepTracker {
    private HashMap<UUID, Long> lastSleepTimes = new HashMap<>();
    private final RealisticDreams plugin;
    String path = "Sleep Tracker";

    public SleepTracker(RealisticDreams plugin) {
        this.plugin = plugin;
    }

    public void registerSleep(Player player) {
        UUID playerId = player.getUniqueId();
        String trackerSetting = plugin.getConfig().getString(path);
        long currentTime = System.currentTimeMillis();

        if (Objects.equals(trackerSetting, "true")) {
            getLastSleep(player);
        } else {
            player.sendMessage("§cSleep tracking is currently disabled.");
        }

        lastSleepTimes.put(playerId, currentTime);
    }

    public void getLastSleep(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        String trackerSetting = plugin.getConfig().getString(path);

        if (!lastSleepTimes.containsKey(playerId)) {
            player.sendMessage("§eYou have not yet slept in this session.");
        } else if (lastSleepTimes.containsKey(playerId) && Objects.equals(trackerSetting, "true")) {
            long lastSleepTime = lastSleepTimes.get(playerId);
            long timeSinceLastSleep = currentTime - lastSleepTime;
            long timeInMinutes = timeSinceLastSleep / 1000 / 60;

            player.sendMessage(TF.format("You last slept " + timeInMinutes + " minutes ago.", TF.YELLOW));
        } else {
            player.sendMessage(TF.format("Sleep tracking is currently disabled.", TF.YELLOW));
        }
    }
}
