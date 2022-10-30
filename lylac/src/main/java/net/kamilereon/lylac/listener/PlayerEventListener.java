package net.kamilereon.lylac.listener;

import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

import net.kamilereon.lylac.LylacMemory;
import net.kamilereon.lylac.event.player.ArmorEquipEvent;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();
        LylacMemory.registerLylacPlayer(joinedPlayer);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player quittedPlayer = event.getPlayer();
        LylacMemory.unregisterLylacPlayer(quittedPlayer);
    }

    @EventHandler
    public void PlayerClickEvent(PlayerInteractEvent event) {
        Action action = event.getAction();
        EquipmentSlot eSlot = event.getHand();
        if(eSlot == EquipmentSlot.OFF_HAND) return;
    }

    @EventHandler
    public void PlayerArmorEquipEvent(ArmorEquipEvent event) {

    }
    
    @EventHandler
    public void changeArmorEvent(InventoryClickEvent event) {
        
    }
}
