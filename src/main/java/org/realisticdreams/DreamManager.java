package org.realisticdreams;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.realisticdreams.utility.RandomText;
import org.realisticdreams.utility.TF;
import org.realisticdreams.quests.QuestAssignmentHandler;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DreamManager {
    private final RealisticDreams plugin;
    private final Random random = new Random();
    public final QuestAssignmentHandler questAssignmentHandler = new QuestAssignmentHandler();
    private RandomText randomText = new RandomText();

    public DreamManager(RealisticDreams plugin) {
        this.plugin = plugin;
    }

    private enum DURATION {
        SHORT(200), // 10 seconds
        MEDIUM(400), // 20 seconds
        LONG(1200); // 1 minute
        private final int ticks;

        DURATION(int ticks) {
            this.ticks = ticks;
        }

        public int getTicks() {
            return ticks;
        }
    }

    private enum INTENSITY {
        LOW(1), MEDIUM(2), HIGH(3);
        private final int level;

        INTENSITY(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    public String getRandomQuote(String quotesGroup, String type) {
        List<String> quotes = plugin.getConfig().getStringList("Quotes." + quotesGroup);
        if (!quotes.isEmpty()) {
            String selectedText = quotes.get(random.nextInt(quotes.size()));
            return TF.format(selectedText, evaluateType(type), false, true);
        }
        return "No quote found.";
    }

    private String evaluateType(String type) {
        return "GOOD".equalsIgnoreCase(type) ? TF.GREEN : TF.RED;
    }

    public void applyDream(Player player) {
        if (player == null) {
            plugin.getLogger().warning("Attempted to apply dream to a null player.");
            return;
        }

        // Generating a random DreamType
        DreamType dreamType = DreamType.values()[random.nextInt(DreamType.values().length)];
        String path = "Dreams.Category." + dreamType.name();

        // Retrieve the configuration for the dream type
        String quotesGroup = plugin.getConfig().getString(path + ".QuotesGroup");
        String message = plugin.getConfig().getString(path + ".Message");
        String type = plugin.getConfig().getString(path + ".Type");
        PotionEffectType effectType = PotionEffectType.getByName(Objects.requireNonNull(plugin.getConfig().getString(path + ".Effect")).toUpperCase());
        DURATION duration = DURATION.valueOf(plugin.getConfig().getString(path + ".Duration").toUpperCase());
        INTENSITY intensity = INTENSITY.valueOf(plugin.getConfig().getString(path + ".Intensity").toUpperCase());

        // Apply potion effect
        if (effectType != null) {
            player.addPotionEffect(new PotionEffect(effectType, duration.getTicks(), intensity.getLevel()));
        } else {
            plugin.getLogger().warning("Invalid potion effect type for dream type: " + dreamType.name());
        }

        // Send nightly summary to player
        player.sendMessage(TF.format(message, evaluateType(type)));
        player.sendMessage(getRandomQuote(quotesGroup, type));

        // Handle additional logic for specific dream types
        if (dreamType == DreamType.ADVENTUROUS) {
            questAssignmentHandler.assignAdventureQuest(player);
        }
    }

}
