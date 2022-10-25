package net.kamilereon.lylac;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Utils {
    public static class Chat {
        public static void sendActionBar(Player target, String text) {
            target.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
        }
    }
}
