package org.realisticdreams;

import org.realisticdreams.quests.QuestAssignmentHandler;
import org.realisticdreams.quests.QuestCommandExecutor;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class RealisticDreams extends JavaPlugin implements Listener {

    private DreamManager dreamManager = new DreamManager();
    private SleepTracker sleepTracker = new SleepTracker();

    @Override
    public void onEnable() {
        this.getCommand("checkquest").setExecutor(new QuestCommandExecutor(dreamManager));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            sleepTracker.getLastSleep(event.getPlayer());
            dreamManager.questAssignmentHandler.failQuest(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerWake(PlayerBedLeaveEvent event) {
        dreamManager.applyDream(event.getPlayer());
    }
}
