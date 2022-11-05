package net.kamilereon.lylac;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class LylacUtils {
    public static class Chat {
        public static void sendActionBar(Player target, String text) {
            target.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
        }
    }
    public static class ActionBar {
        public static void warning(Player target, String text) {
            TextComponent textComponent = new TextComponent(text);
            textComponent.setColor(ChatColor.RED);
            target.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
        }
    }
    public static class Scheduler {
        public static BukkitTask executeAfterTick(Runnable runnable, int tick) {
            return Bukkit.getScheduler().runTaskLater(Lylac.lylacPlugin, runnable, tick);
        }
        public static BukkitTask executeContinuallyEveryTick(Runnable runnable, int tick) {
            return Bukkit.getScheduler().runTaskTimer(Lylac.lylacPlugin, runnable, 1, tick);
        }
    }
    public static class Event {
        public static void callEvent(org.bukkit.event.Event event) {
            Lylac.lylacPlugin.getServer().getPluginManager().callEvent(event);
        }
        public static void registerEvent(Listener listener) {
            Lylac.lylacPlugin.getServer().getPluginManager().registerEvents(listener, Lylac.lylacPlugin);
        }
    }
}
