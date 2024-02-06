package org.realisticdreams;

import org.realisticdreams.quests.QuestCommandExecutor;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.realisticdreams.sleep.SleepTracker;
import org.realisticdreams.sleep.SleepTrackerCommandExecutor;

import java.util.HashMap;
import java.util.UUID;

public final class RealisticDreams extends JavaPlugin implements Listener {

    private DreamManager dreamManager;
    private SleepTracker sleepTracker;
    private HashMap<UUID, Long> sleepStartTimes = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        dreamManager = new DreamManager(this);
        sleepTracker = new SleepTracker(this);

        this.getCommand("checkquest").setExecutor(new QuestCommandExecutor(dreamManager));
        this.getCommand("lastslept").setExecutor(new SleepTrackerCommandExecutor(sleepTracker));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            sleepStartTimes.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onPlayerWake(PlayerBedLeaveEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        if (sleepStartTimes.containsKey(playerId)) {
            long sleepDuration = System.currentTimeMillis() - sleepStartTimes.get(playerId);
            if (sleepDuration >= 5000) {
                sleepTracker.registerSleep(event.getPlayer());
                dreamManager.questAssignmentHandler.failQuest(event.getPlayer());
                dreamManager.applyDream(event.getPlayer());
            }

            sleepStartTimes.remove(playerId);
        }
    }
}
