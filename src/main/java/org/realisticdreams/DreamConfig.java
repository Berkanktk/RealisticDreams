package org.realisticdreams;

import org.bukkit.potion.PotionEffectType;
import org.realisticdreams.utility.TF;

import java.util.List;

class DreamConfig {
    private PotionEffectType effect;
    private int duration;
    private int intensity;
    private String message;
    private TF messageType;
    private List<String> quotes;

    public DreamConfig(PotionEffectType effect, int duration, int intensity, String message, List<String> quotes, TF messageType) {
        this.effect = effect;
        this.duration = duration;
        this.intensity = intensity;
        this.message = message;
        this.quotes = quotes;
        this.messageType = messageType;
    }

    public PotionEffectType getEffect() {
        return effect;
    }

    public void setEffect(PotionEffectType effect) {
        this.effect = effect;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int durationTicks) {
        this.duration = durationTicks;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TF getMessageType() {
        return messageType;
    }

    public void setMessageType(TF messageType) {
        this.messageType = messageType;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
}
