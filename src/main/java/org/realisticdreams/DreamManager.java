package org.realisticdreams;

import org.bukkit.Material;
import org.realisticdreams.quests.Quest;
import org.realisticdreams.quests.QuestManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.*;

public class DreamManager {
    private Random random = new Random();

    public void failQuest(Player player) {
        UUID playerId = player.getUniqueId();
        if (QuestManager.hasActiveQuest(playerId)) {
            QuestManager.removeQuest(playerId);
            player.sendMessage(redColor + boldText + "Your quest has failed as you went to sleep.");
        }
    }

    private void assignAdventureQuest(Player player) {
        Entry<Material, Integer> questItem = questItems.get(random.nextInt(questItems.size()));
        Material item = questItem.getKey();
        int quantity = questItem.getValue();

        Quest adventureQuest = new Quest("Collect " + item.name(), player.getUniqueId(), item, quantity);
        QuestManager.assignQuest(player.getUniqueId(), adventureQuest);

        player.sendMessage("Your quest: Collect " + quantity + " " + item.name());
    }

    private final List<Entry<Material, Integer>> questItems = Arrays.asList(
            new SimpleEntry<>(Material.IRON_INGOT, 10),
            new SimpleEntry<>(Material.GOLD_INGOT, 5),
            new SimpleEntry<>(Material.DIAMOND, 1),
            new SimpleEntry<>(Material.COAL, 12),
            new SimpleEntry<>(Material.OAK_LOG, 32),
            new SimpleEntry<>(Material.BONE, 8),
            new SimpleEntry<>(Material.COPPER_INGOT, 15)
    );

    private final List<String> existentialQuotes = Arrays.asList(
            "To live is to experience. Without experience, one's life is meaningless.",
            "The adventure of life is to learn. The purpose of life is to grow.",
            "In seeking meaning, we find ourselves.",
            "Our existence is an adventure filled with wonder and mystery.",
            "Life's true journey begins when you start exploring.",
            "The beauty of life is in its mysteries. Seek and you shall find.",
            "Freedom is the essence of existence. With every choice, we define ourselves.",
            "Every block placed, every step taken, is a part of your story.",
            "In the game of life and Minecraft, the journey is the reward.",
            "Embrace the unknown, for it is the birthplace of discovery.",
            "What we build shapes us just as much as we shape it.",
            "In every end, there is also a beginning.",
            "The paths we choose reveal the true nature of our world and ourselves.",
            "True discovery lies not in seeking new landscapes, but in having new eyes.",
            "Every journey is an opportunity to understand more than before.",
            "Our greatest adventures are those we have yet to embark upon.",
            "In the realm of blocks, each choice carves out our destiny.",
            "The world is but a canvas to our imagination.",
            "Dreams shape the world; keep dreaming, keep building.",
            "In the depths of the mines, we discover not just resources, but insights."
    );

    String redColor = "§c";
    String greenColor = "§a";
    String boldText = "§l";

    // Duration of potion effects is in ticks, 20 ticks = 1 second
    // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html
    public void applyDream(Player player) {
        DreamType dreamType = DreamType.values()[random.nextInt(DreamType.values().length)];

        switch (dreamType) {
            case GOOD:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1200, 1));
                player.sendMessage(greenColor + "You have a pleasant dream.");
                break;
            case ADVENTUROUS:
                assignAdventureQuest(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1));
                player.sendMessage(greenColor + "You dream of grand adventures!");
                break;
            case WEIRD:
                player.sendMessage(redColor + "You have a bizarre and inexplicable dream!");
                break;
            case NIGHTMARE:
                player.damage(4);
                player.sendMessage(redColor + "You wake up from a terrible nightmare!");
                break;
            case EXISTENTIAL:
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
                player.sendMessage(greenColor + "You had an existential dream.");
                player.sendMessage(existentialQuotes.get(random.nextInt(existentialQuotes.size())));
                break;
            case ROMANCE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 1));
                player.sendMessage(greenColor + "You dream of a romantic encounter.");
                break;
            case FAMILY:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1));
                player.sendMessage(greenColor + "You dream of your family.");
                break;
            case SLEEP_DISORDER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 1));
                player.sendMessage(redColor + "You dream of not being able to sleep.");
                break;
            case FLYING:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
                player.sendMessage(redColor + "You dreamt you were flying. You have wings! Or do you?");
                break;
        }
    }
}
