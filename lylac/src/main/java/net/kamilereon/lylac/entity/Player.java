package net.kamilereon.lylac.entity;

import org.bukkit.Bukkit;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.Utils;

/**
 * @author kamilereon
 * @version 1.0.0
 */
public class Player extends Entity<org.bukkit.entity.Player> {

    protected int maxMana;
    protected int mana;

    protected int manaRegenRate = 100;
    protected int manaConsumptionRate = 100;

    protected int spellCastingSpeed = 100;
    protected int speedWhileSpellCasting = 100;

    protected int spellAmplificationRate = 100;
    protected int waterAmplificationRate = 100;
    protected int fireAmplificationRate = 100;
    protected int airAmplificationRate = 100;
    protected int earthAmplificationRate = 100;
    
    public Player(int maxHealth, org.bukkit.entity.Player bukkitEntity) {
        super(maxHealth, bukkitEntity);
        this.init();
    }

    public void update() {
        org.bukkit.entity.Player player = this.getBukkitEntity();
        player.setHealth(this.health);
    }

    public void init() {
        Bukkit.getScheduler().runTask(Lylac.lylacPlugin, () -> {

            update();
        });
    }

    public void fin() {

    }

    public void showStatusAsActionBar() {
        Utils.Chat.sendActionBar(bukkitEntity, "");
    }
}
