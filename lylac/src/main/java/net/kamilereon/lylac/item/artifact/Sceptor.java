package net.kamilereon.lylac.item.artifact;

import java.util.UUID;

import org.bukkit.Material;

import net.kamilereon.lylac.element.ElementDamage;

public abstract class Sceptor extends Artifact {

    private ElementDamage elementDamage;

    public Sceptor(UUID uuid, Material material, ElementDamage elementDamage) {
        super(uuid, ArtifactType.SCEPTOR, material);
        this.elementDamage = elementDamage;
    }

    public ElementDamage getElementDamage() {
        return this.elementDamage;
    }
}
