package net.kamilereon.lylac.item.artifact;

import net.kamilereon.lylac.entity.Player;

/**
 * @author kamilereon
 * @version 1.0.0
 */
public class ArtifactInventory {

    private final Player inventoryHolder;

    private Artifact helmet = null;
    private Artifact chestPlate = null;
    private Artifact leggings = null;
    private Artifact boots = null;
    private Artifact earing = null;
    private Artifact necklace = null;
    private Artifact ring1 = null;
    private Artifact ring2 = null;

    public ArtifactInventory(Player player) {
        this.inventoryHolder = player;
    }

    public Artifact getHelmet() {
        return helmet;
    }
    public void setHelmet(Artifact helmet) {
        this.helmet = helmet;
    }
    public Artifact getChestPlate() {
        return chestPlate;
    }
    public void setChestPlate(Artifact chestPlate) {
        this.chestPlate = chestPlate;
    }
    public Artifact getLeggings() {
        return leggings;
    }
    public void setLeggings(Artifact leggings) {
        this.leggings = leggings;
    }
    public Artifact getBoots() {
        return boots;
    }
    public void setBoots(Artifact boots) {
        this.boots = boots;
    }
    public Artifact getEaring() {
        return earing;
    }
    public void setEaring(Artifact earing) {
        this.earing = earing;
    }
    public Artifact getNecklace() {
        return necklace;
    }
    public void setNecklace(Artifact necklace) {
        this.necklace = necklace;
    }
    public Artifact getRing1() {
        return ring1;
    }
    public void setRing1(Artifact ring1) {
        this.ring1 = ring1;
    }
    public Artifact getRing2() {
        return ring2;
    }
    public void setRing2(Artifact ring2) {
        this.ring2 = ring2;
    }

    /*
     * 모든 아티팩트들에 대해서 재계산
     * 아티팩트들을 교체할 때 자동으로 실행
     */
    public void reCalculateStats() {
        
    }
}