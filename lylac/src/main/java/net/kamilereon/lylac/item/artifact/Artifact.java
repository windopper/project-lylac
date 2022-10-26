package net.kamilereon.lylac.item.artifact;

import java.util.UUID;

import org.bukkit.Material;

import net.kamilereon.lylac.item.Item;

public class Artifact extends Item {

    private final ArtifactType artifactType;

    private Artifact(UUID uuid, ArtifactType type, Material material) {
        super(uuid, material);
        this.artifactType = type;
    }

    public static Artifact builder(UUID uuid, ArtifactType type, Material material) {
        return new Artifact(uuid, type, material);
    }

    public ArtifactType getArtifactType() {
        return this.artifactType;
    }

    public enum ArtifactType {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        EARING,
        NECKLACE,
        RING,
        SCEPTOR
    }
}
