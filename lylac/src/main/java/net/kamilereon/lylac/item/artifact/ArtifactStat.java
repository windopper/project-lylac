package net.kamilereon.lylac.item.artifact;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.entity.Entity.EntityStats;
import net.kamilereon.lylac.item.artifact.Artifact.ArtifactType;

/**
 * 
 * {@link Artifact}의 세부 스탯에 관한 클래스
 * 
 * <p>모든 필드 값은 {@link Player} 객체의 동일이름의 필드에 대해서 "합연산" 으로 계산 될 값이다.</p>
 * 
 * @author kamilereon
 * @version 1.0.0
 * @see Artifact
 * @see ArtifactInventory
 * @see Player
 */
public class ArtifactStat {

    private int maxMana = 0;
    private int maxManaIncRate = 0;
    private int manaRegenRate = 0;
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
     * 아티팩트의 스탯을 설정하게 해주는 setter 메서드
     * 
     * @param stats 설정하고자 하는 스탯
     * @param value 설정하고자 하는 값
     * @return 해당 객체 반환
     */
    public ArtifactStat setStat(EntityStats stats, int value) {
        try {
            Field field = this.getClass().getDeclaredField(stats.name());
            field.setAccessible(true);
            field.setInt(this, value);
            field.setAccessible(false);
        }
        catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage(e.getStackTrace().toString());
        }
        return this;
    }

    /**
     * 
     * @param stats 아티팩트의 스탯에서 가져오고 싶은 스탯
     * @return 해당 스탯과 이름이 같은 필드 값을 리턴
     * @throws NoSuchFieldException 설정하고자 하는 스탯이 해당 필드에 존재하지 않을 때 발생하는 예외
     * @throws IllegalAccessException 필드 접근을 하지 못할 때 발생하는 예외.
     */
    public int getStat(EntityStats stats) throws NoSuchFieldException, IllegalAccessException {
        try {
            Field field = this.getClass().getDeclaredField(stats.name());
            field.setAccessible(true);
            int V = field.getInt(this);
            field.setAccessible(false);
            return V;
        }
        catch(NoSuchFieldException | IllegalAccessException e) {
            throw e;
        }
    }

    /* */
}
