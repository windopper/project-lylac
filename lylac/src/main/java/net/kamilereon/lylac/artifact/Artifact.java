package net.kamilereon.lylac.artifact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Artifact {

    private String name = "artifact";
    private final UUID uuid;
    private final ArtifactType artifactType;
    private final Material material;

    private final List<String> lore = new ArrayList<>();
    private Artifact(UUID uuid, ArtifactType type, Material material) {
        this.uuid = uuid;
        this.artifactType = type;
        this.material = material;
    }

    public static Artifact builder(UUID uuid, ArtifactType type, Material material) {
        return new Artifact(uuid, type, material);
    }

    public Artifact addLore(String val) {
        this.lore.add(val);
        return this;
    }

    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
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
