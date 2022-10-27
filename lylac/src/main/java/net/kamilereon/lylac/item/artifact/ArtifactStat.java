package net.kamilereon.lylac.item.artifact;

import java.lang.reflect.Field;

import net.kamilereon.lylac.entity.Player;

/**
 * @author kamilereon
 * @version 1.0.0
 * @apiNote 해당 클래스의 필드 접근 제한 여부를 좀 더 고민해 보는게 좋겠음.
 */
public class ArtifactStat {

    /**
     * 모든 필드 값은 {@link Player} 객체의 동일이름의 필드에 대해서 "합연산" 으로 계산 될 값이다.
     * <p>예1) {@link #maxMana}가 10 이고 {@link Player} 객체의 maxMana가
     *  200일때 플레이어가 가진 모든 {@link #ArtifactStat}을 {@link #getCombinedArtifactStat(ArtifactStat...)} 
     * 을 통하여 계산 후 {@link Player} 객체로 반영하면 {@link #maxMana} 값이 210이 됨</p>
     */

    private int maxMana = 0;
    private int maxManaIncRate = 0;
    private int manaConsumptionRate = 0;

    private int maxHealth = 0;
    private int maxHealthIncRate = 0;
    private int healthRegenRate = 0;

    private int spellCastingSpeed = 0;
    private int speedWhileSpellCasting = 0;

    private int spellResistance = 0;
    private int meleeResistance = 0;
    private int waterResistance = 0;
    private int fireResistance = 0;
    private int airResistance = 0;
    private int earthResistance = 0;

    private int spellAmplificationRate = 0;
    private int waterAmplificationRate = 0;
    private int fireAmplificationRate = 0;
    private int airAmplificationRate = 0;
    private int earthAmplificationRate = 0;

    public static ArtifactStat builder() {
        return new ArtifactStat();
    }

    /**
     * @params artifactStats artifactStat의 모든 필드 값을 합칠 리스트
     * @return 파라미터로 넘어온 artifactStats의 모든 필드 값을 합친 새로운 ArtifactStat 객체
     */
    public static ArtifactStat getCombinedArtifactStat(ArtifactStat ...artifactStats) {

        ArtifactStat combined = ArtifactStat.builder(); 
        Field[] fields = ArtifactStat.class.getDeclaredFields();
        
        for(ArtifactStat artifactStat : artifactStats) {
            for(Field field : fields) {
                try {
                    field.setAccessible(true);
                    String K = field.getName();
                    int V = (int) field.get(artifactStat);
                    Field combinedField = ArtifactStat.class.getDeclaredField(K);
                    combinedField.setAccessible(true);
                    combinedField.set(combined, (int) combinedField.get(combined) + V);
                }
                catch(Exception e) {

                }
            }
        }
        return combined;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMaxManaIncRate() {
        return maxManaIncRate;
    }

    public void setMaxManaIncRate(int maxManaIncRate) {
        this.maxManaIncRate = maxManaIncRate;
    }

    public int getManaConsumptionRate() {
        return manaConsumptionRate;
    }

    public void setManaConsumptionRate(int manaConsumptionRate) {
        this.manaConsumptionRate = manaConsumptionRate;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxHealthIncRate() {
        return maxHealthIncRate;
    }

    public void setMaxHealthIncRate(int maxHealthIncRate) {
        this.maxHealthIncRate = maxHealthIncRate;
    }

    public int getHealthRegenRate() {
        return healthRegenRate;
    }

    public void setHealthRegenRate(int healthRegenRate) {
        this.healthRegenRate = healthRegenRate;
    }

    public int getSpellCastingSpeed() {
        return spellCastingSpeed;
    }

    public void setSpellCastingSpeed(int spellCastingSpeed) {
        this.spellCastingSpeed = spellCastingSpeed;
    }

    public int getSpeedWhileSpellCasting() {
        return speedWhileSpellCasting;
    }

    public void setSpeedWhileSpellCasting(int speedWhileSpellCasting) {
        this.speedWhileSpellCasting = speedWhileSpellCasting;
    }

    public int getSpellResistance() {
        return spellResistance;
    }

    public void setSpellResistance(int spellResistance) {
        this.spellResistance = spellResistance;
    }

    public int getMeleeResistance() {
        return meleeResistance;
    }

    public void setMeleeResistance(int meleeResistance) {
        this.meleeResistance = meleeResistance;
    }

    public int getWaterResistance() {
        return waterResistance;
    }

    public void setWaterResistance(int waterResistance) {
        this.waterResistance = waterResistance;
    }

    public int getFireResistance() {
        return fireResistance;
    }

    public void setFireResistance(int fireResistance) {
        this.fireResistance = fireResistance;
    }

    public int getAirResistance() {
        return airResistance;
    }

    public void setAirResistance(int airResistance) {
        this.airResistance = airResistance;
    }

    public int getEarthResistance() {
        return earthResistance;
    }

    public void setEarthResistance(int earthResistance) {
        this.earthResistance = earthResistance;
    }

    public int getSpellAmplificationRate() {
        return spellAmplificationRate;
    }

    public void setSpellAmplificationRate(int spellAmplificationRate) {
        this.spellAmplificationRate = spellAmplificationRate;
    }

    public int getWaterAmplificationRate() {
        return waterAmplificationRate;
    }

    public void setWaterAmplificationRate(int waterAmplificationRate) {
        this.waterAmplificationRate = waterAmplificationRate;
    }

    public int getFireAmplificationRate() {
        return fireAmplificationRate;
    }

    public void setFireAmplificationRate(int fireAmplificationRate) {
        this.fireAmplificationRate = fireAmplificationRate;
    }

    public int getAirAmplificationRate() {
        return airAmplificationRate;
    }

    public void setAirAmplificationRate(int airAmplificationRate) {
        this.airAmplificationRate = airAmplificationRate;
    }

    public int getEarthAmplificationRate() {
        return earthAmplificationRate;
    }

    public void setEarthAmplificationRate(int earthAmplificationRate) {
        this.earthAmplificationRate = earthAmplificationRate;
    }

    /* */
}
