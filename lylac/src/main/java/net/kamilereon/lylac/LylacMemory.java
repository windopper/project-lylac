package net.kamilereon.lylac;

import java.util.HashMap;
import java.util.Map;

import net.kamilereon.lylac.entity.Entity;
import net.kamilereon.lylac.entity.Player;

public class LylacMemory {

    private final static Map<org.bukkit.entity.Player, Player> PLAYERS = new HashMap<>();
    private final static Map<org.bukkit.entity.LivingEntity, Entity> ENTITIES = new HashMap<>();

    private final static Object LOCK_PLAYER = new Object();
    private final static Object LOCK_ENTITIES = new Object();

    public static void registerLylacPlayer(org.bukkit.entity.Player player) {
        synchronized(LOCK_PLAYER) {
            PLAYERS.put(player, new Player(player));
        }
    }

    public static Player getLylacPlayer(org.bukkit.entity.Player player) {
        synchronized(LOCK_PLAYER) {
            return PLAYERS.get(player);
        }   
    }

    public static void unregisterLylacPlayer(org.bukkit.entity.Player player) {
        synchronized(LOCK_PLAYER) {
            PLAYERS.remove(player);
        }
    }
}
