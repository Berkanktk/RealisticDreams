package org.realisticdreams;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.realisticdreams.utility.TF;
import org.realisticdreams.quests.QuestAssignmentHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DreamManager {
    private final Random random = new Random();
    public final QuestAssignmentHandler questAssignmentHandler = new QuestAssignmentHandler();

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
                player.sendMessage(TF.format("You dreamt of grand adventures!", TF.GREEN));
                questAssignmentHandler.assignAdventureQuest(player);
                break;
            case WEIRD:
                player.sendMessage(TF.format("You have a bizarre and inexplicable dream!", TF.RED));
                break;
            case NIGHTMARE:
                player.damage(4);
                player.sendMessage(TF.format("You wake up from a terrible nightmare!", TF.RED));
                break;
            case EXISTENTIAL:
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
                player.sendMessage(TF.format("You had an existential dream.", TF.GREEN));
                player.sendMessage(TF.format(existentialQuotes.get(random.nextInt(existentialQuotes.size())), TF.GREEN, false, true));
                break;
            case ROMANCE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 1));
                player.sendMessage(TF.format("You dreamt of a romantic encounter.", TF.GREEN));
                break;
            case FAMILY:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1));
                player.sendMessage(TF.format("You dreamt of your family.", TF.GREEN));
                break;
            case SLEEP_DISORDER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 1));
                player.sendMessage(TF.format("You dreamt of not being able to sleep.", TF.RED));
                break;
            case FLYING:
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
                player.sendMessage(TF.format("You dreamt you were flying. You have wings! Or do you?", TF.RED));
                break;
        }
    }
}
