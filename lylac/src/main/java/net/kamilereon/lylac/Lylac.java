package net.kamilereon.lylac;

import java.util.EventListener;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.kamilereon.lylac.listener.ArmorListener;
import net.kamilereon.lylac.listener.PlayerEventListener;

public class Lylac extends JavaPlugin {

    public static final Plugin lylacPlugin = JavaPlugin.getPlugin(Lylac.class);

    @Override
    public void onEnable() {
        LylacUtils.Event.registerEvent(new ArmorListener(getConfig().getStringList("blocked")));
        LylacUtils.Event.registerEvent(new PlayerEventListener());
    }
    @Override
    public void onDisable() {
        
    }

    public void loop() {

    }
}
