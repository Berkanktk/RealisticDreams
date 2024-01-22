package org.realisticdreams;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.realisticdreams.utility.QuotesLoader;
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
    private QuotesLoader quotesLoader = new QuotesLoader();

    List<String> existentialQuotes = quotesLoader.getQuotes("existentialQuotes");
    List<String> romanceQuotes = quotesLoader.getQuotes("romanceQuotes");
    List<String> familyQuotes = quotesLoader.getQuotes("familyQuotes");
    List<String> sleepDisorderQuotes = quotesLoader.getQuotes("sleepDisorderQuotes");
    List<String> weirdQuotes = quotesLoader.getQuotes("weirdQuotes");
    List<String> adventurousQuotes = quotesLoader.getQuotes("adventurousQuotes");
    List<String> flyingQuotes = quotesLoader.getQuotes("flyingQuotes");
    List<String> nightmareQuotes = quotesLoader.getQuotes("nightmareQuotes");

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

    // Duration of potion effects is in ticks, 20 ticks = 1 second
    // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html
    public void applyDream(Player player) {
        DreamType dreamType = DreamType.values()[random.nextInt(DreamType.values().length)];

        switch (dreamType) {
            case GOOD:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1200, 1));
                player.sendMessage(TF.format("You had a pleasant dream.", TF.GREEN));
                break;
            case ADVENTUROUS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1));
                player.sendMessage(TF.format("You dreamt of great adventures!", TF.GREEN));
                player.sendMessage(randomText.getRandomText(adventurousQuotes, RandomText.DREAM.GOOD));
                questAssignmentHandler.assignAdventureQuest(player);
                break;
            case WEIRD:
                player.sendMessage(TF.format("You had an bizarre and inexplicable dream.", TF.RED));
                player.sendMessage(randomText.getRandomText(weirdQuotes, RandomText.DREAM.BAD));
                break;
            case NIGHTMARE:
                player.sendMessage(TF.format("You woke up from a terrible nightmare!", TF.RED));
                player.sendMessage(randomText.getRandomText(nightmareQuotes, RandomText.DREAM.BAD));
                break;
            case EXISTENTIAL:
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
                player.sendMessage(TF.format("You had an existential dream.", TF.GREEN));
                player.sendMessage(randomText.getRandomText(existentialQuotes, RandomText.DREAM.GOOD));
                break;
            case ROMANCE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 1));
                player.sendMessage(TF.format("You dreamt of a romantic encounter.", TF.GREEN));
                player.sendMessage(randomText.getRandomText(romanceQuotes, RandomText.DREAM.GOOD));
                break;
            case FAMILY:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1));
                player.sendMessage(TF.format("You dreamt of your family.", TF.GREEN));
                player.sendMessage(randomText.getRandomText(familyQuotes, RandomText.DREAM.GOOD));
                break;
            case SLEEP_DISORDER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 1));
                player.sendMessage(TF.format("You were not able to sleep.", TF.RED));
                player.sendMessage(randomText.getRandomText(sleepDisorderQuotes, RandomText.DREAM.BAD));
                break;
            case FLYING:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
                player.sendMessage(TF.format("You dreamt you were flying. You have wings! Or do you?", TF.RED));
                player.sendMessage(randomText.getRandomText(flyingQuotes, RandomText.DREAM.BAD));
                break;
        }
    }
}
