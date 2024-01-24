package org.realisticdreams;

import org.realisticdreams.quests.QuestCommandExecutor;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.realisticdreams.sleep.SleepTracker;
import org.realisticdreams.sleep.SleepTrackerCommandExecutor;

public final class RealisticDreams extends JavaPlugin implements Listener {

    private DreamManager dreamManager;
    private SleepTracker sleepTracker;

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
            sleepTracker.registerSleep(event.getPlayer());

            // If the player has an active quest, fail it
            dreamManager.questAssignmentHandler.failQuest(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerWake(PlayerBedLeaveEvent event) {
        dreamManager.applyDream(event.getPlayer());
    }
}
