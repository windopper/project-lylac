package net.kamilereon.lylac.item.artifact;

import java.util.UUID;

import org.bukkit.Material;

import net.kamilereon.lylac.item.Item;

public class Artifact extends Item {

    private final ArtifactType artifactType;
    private ArtifactStat artifactStat = new ArtifactStat();

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

    public ArtifactStat getArtifactStat() {
        return artifactStat;
    }

    public void setArtifactStat(ArtifactStat artifactStat) {
        this.artifactStat = artifactStat;
    }

    public enum ArtifactType {
        
        HELMET("helmet"),
        CHESTPLATE("chestplate"),
        LEGGINGS("leggings"),
        BOOTS("boots"),
        EARING("earing"),
        NECKLACE("necklace"),
        RING1("ring1"),
        RING2("ring2"),
        SCEPTOR("sceptor");

        final String name;
        ArtifactType(String name) {
            this.name = name;
        }
    }
}
