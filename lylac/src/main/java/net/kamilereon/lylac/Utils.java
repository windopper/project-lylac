package net.kamilereon.lylac;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Utils {
    public static class Chat {
        public static void sendActionBar(Player target, String text) {
            target.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
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
    }
}
