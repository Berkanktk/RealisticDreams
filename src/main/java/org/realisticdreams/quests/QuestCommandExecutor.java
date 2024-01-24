package org.realisticdreams.quests;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.realisticdreams.DreamManager;
import org.realisticdreams.utility.TF;

public class QuestCommandExecutor implements CommandExecutor {
    private final DreamManager dreamManager;

    public QuestCommandExecutor(DreamManager dreamManager) {
        this.dreamManager = dreamManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("checkquest")) {
                Quest quest = QuestManager.getActiveQuest(player.getUniqueId());
                if (quest != null && quest.getObjectiveItem() != null) {
                    if (player.getInventory().containsAtLeast(new ItemStack(quest.getObjectiveItem()), quest.getObjectiveAmount())) {
                        player.getInventory().removeItem(new ItemStack(quest.getObjectiveItem(), quest.getObjectiveAmount()));
                        player.giveExp(quest.getExpReward());
                        player.sendMessage(TF.format("Quest completed! You have been rewarded with " + quest.getExpReward() + " experience points.", TF.GREEN));
                        QuestManager.removeQuest(player.getUniqueId());
                    } else {
                        player.sendMessage(TF.format("You do not have enough of the required item.", TF.RED));
                    }
                } else {
                    player.sendMessage(TF.format("You do not have an active quest.", TF.RED));
                }
                return true;
            }
        }
        return false;
    }
}
