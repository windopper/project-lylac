package net.kamilereon.lylac.item.artifact;

import java.util.UUID;

import org.bukkit.Material;

import net.kamilereon.lylac.element.ElementDamageRange;

public abstract class Sceptor extends Artifact {

    private ElementDamageRange elementDamage;

    public Sceptor(UUID uuid, Material material, ElementDamageRange elementDamage) {
        super(uuid, ArtifactType.SCEPTOR, material);
        this.elementDamage = elementDamage;
    }

    public ElementDamageRange getElementDamage() {
        return this.elementDamage;
    }
}
