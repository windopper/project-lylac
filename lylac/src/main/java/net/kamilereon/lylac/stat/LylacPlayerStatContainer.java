package net.kamilereon.lylac.stat;

import java.lang.reflect.Field;

import net.kamilereon.lylac.LylacUtils;
import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.event.player.LylacPlayerUpdateStatEvent;

/**
 * 플레이어의 능력치에 관한 클래스
 * <p>플레이어의 강함에 직접적으로 연관 되어 있음</p>
 * <p>현재 체력, 현재 마나 같이 지속적으로 변화하는 값 또한 해당 클래스에 존재</p>
 * 
 * <b>해당 클래스 필드 값을 변경 할 때는 {@link #setStat(LylacPlayerStats, int)}을 통하여 할 것!!</b>
 * <p/>
 * <b>{@link #setStat(LylacPlayerStats, int)}을 통하여 필드 값을 설정해야 <code>LylacPlayerUpdateStatEvent</code>
 * 이벤트가 송신됨</b>
 */
public class LylacPlayerStatContainer {

    private static final int RATE_DEFAULT = 100;

    private final Player player;

    public int mental = 0;
    public int amplification = 0;
    public int vacuity = 0;
    public int chaos = 0;
    public int precision = 0;

    public int additionalMental = 0;
    public int additionalAmplification = 0;
    public int additionalVacuity = 0;
    public int additionalChaos = 0;
    public int additionalPrecision = 0;

    public int health = 100;
    public int maxHealth = LylacPlayerStats.maxHealth.getDefaultValue();
    public int maxHealthIncRate = LylacPlayerStats.maxHealthIncRate.getDefaultValue();
    public int healthRegenRate = LylacPlayerStats.healthRegenRate.getDefaultValue();

    public int mana = 100;
    public int maxMana = 100;
    public int maxManaIncRate = LylacPlayerStats.maxManaIncRate.getDefaultValue();
    public int manaRegenRate = LylacPlayerStats.manaRegenRate.getDefaultValue();
    public int manaConsumptionRate = LylacPlayerStats.manaConsumptionRate.getDefaultValue();

    public int spellCastingSpeed = LylacPlayerStats.spellCastingSpeed.getDefaultValue();
    public int speedWhileSpellCasting = LylacPlayerStats.speedWhileSpellCasting.getDefaultValue();
    public int spellResistance = LylacPlayerStats.spellResistance.getDefaultValue();
    public int spellAmplificationRate = LylacPlayerStats.spellAmplificationRate.getDefaultValue();

    public LylacPlayerStatContainer(Player player) {
        this.player = player;
    }

    public void setStat(LylacPlayerStats stat, int value) {
        try {
            Field field = this.getClass().getField(stat.name());
            field.set(this, value);
            LylacUtils.Event.callEvent(new LylacPlayerUpdateStatEvent(player));
        }
        catch(Exception e) {

        }
    }

        /**
     * 플레이어가 가진 모든 능력치의 이름과 초기값을 저장한 enum 클래스
     * 
     * <p>{@link #getDefaultValue()}를 통하여 해당 능력치 이름의 초기값을 구할 수 있다.</p>
     * <p>{@link #getMin()} 또는 {@link #getMax()}를 통하여 해당 능력치의 최소, 최댓값을 구할 수 있다.</p>
     */
    public enum LylacPlayerStats {
    
        additionalMental(0, -999, 999),
        additionalAmplification(0, -999, 999),
        additionalVacuity(0, -999, 999),
        additionalChaos(0, -999, 999),
        additionalPrecision(0, -999, 999),
        maxMana(100),
        maxManaIncRate(LylacPlayerStatContainer.RATE_DEFAULT),
        manaRegenRate(LylacPlayerStatContainer.RATE_DEFAULT),
        manaConsumptionRate(LylacPlayerStatContainer.RATE_DEFAULT, 10, 10000),
        maxHealth(1000),
        maxHealthIncRate(LylacPlayerStatContainer.RATE_DEFAULT),
        healthRegenRate(LylacPlayerStatContainer.RATE_DEFAULT),
        spellCastingSpeed(LylacPlayerStatContainer.RATE_DEFAULT, 20, 10000),
        speedWhileSpellCasting(LylacPlayerStatContainer.RATE_DEFAULT),
        spellResistance(LylacPlayerStatContainer.RATE_DEFAULT),
        meleeResistance(LylacPlayerStatContainer.RATE_DEFAULT),
        spellAmplificationRate(LylacPlayerStatContainer.RATE_DEFAULT);
    
        private final int defaultValue;
        private int min = 0;
        private int max = 999999;
    
        LylacPlayerStats(int defaultValue) {
            this.defaultValue = defaultValue;
        }
    
        LylacPlayerStats(int defaultValue, int min, int max) {
            this.defaultValue = defaultValue;
            this.min = min;
            this.max = max;
        }
    
        public int getDefaultValue() {
            return this.defaultValue;
        }
    
        public int getMin() {
            return this.min;
        }
    
        public int getMax() {
            return this.max;
        }
    }

    public static class StatUtil {
        /**
         * 해당 능력치를 비율로 환산하여 실제 게임에 적용 될 수 있도록 해줌
         * 
         * @param value 최소값 0,
         * @return value를 100으로 나눈 값으로 리턴
         */
        public static double getValueToRate(int value) {
            return (double) value / 100;
        }
        public static double getValueToRate(int value, boolean inverse) {
            if(!inverse) return getValueToRate(value);
            return (double) (100 / value);
        }
    }
}
