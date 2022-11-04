package net.kamilereon.lylac.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.entity.Player;

public class LylacParticle {

    private final Player player;
    boolean showParticle = true;

    public LylacParticle(Player player) {
        this.player = player;
    }

    public void spawnParticle(Particle particle, Location loc, int count) {
        this.spawnParticle(particle, loc.getX(), loc.getY(), loc.getZ(), count, 0, 0, 0, 0, null);
    }

    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        this.player.getBukkitEntity().spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data);
    }
}
