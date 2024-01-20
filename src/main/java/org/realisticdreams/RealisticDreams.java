package org.realisticdreams;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Random;

public final class RealisticDreams extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Random random = new Random();
            PotionEffectType[] effects = PotionEffectType.values();
            PotionEffectType randomEffect = effects[random.nextInt(effects.length)];
            event.getPlayer().addPotionEffect(new PotionEffect(randomEffect, 600, 1));
        }
    }
}
