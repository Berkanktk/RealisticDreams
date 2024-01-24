package org.realisticdreams.sleep;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.realisticdreams.utility.TF;

public class SleepTrackerCommandExecutor implements CommandExecutor {
    private final SleepTracker sleepTracker;

    public SleepTrackerCommandExecutor(SleepTracker sleepTracker) {
        this.sleepTracker = sleepTracker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("lastslept")) {
                sleepTracker.getLastSleep(player);
            }
        }
        return false;
    }
}
