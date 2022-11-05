package net.kamilereon.lylac.entity;

import java.lang.reflect.Field;

import net.kamilereon.lylac.element.ElementDamageRange;
import net.kamilereon.lylac.element.ElementDamageRange.Range;
import net.kamilereon.lylac.stat.LylacPlayerStatContainer;


/**
 * 라일락 엔티티를 대표하는 최상위 클래스
 * 
 * @Author kamilereon
 * @version 1.0.0
 */
public abstract class Entity {

    protected int level = 0;
    protected ElementDamageRange currentElementDamage = ElementDamageRange.getElementDamageRange().setNeutral(Range.set(10, 10));

    public Entity() {
        this.init();
    }

    /*
     * 플레이어가 소유한 아티팩트, 특성등 능력치에 영향을 주는
     * 모든 값을 계산하여 해당 객체에 반영
     */
    abstract public void update();

    /*
     * 객체가 처음 생산 되었을 때 실행
     */
    abstract protected void init();

    /*
     * 해당 객체의 엔티티가 죽거나 객체가 제거될 때 실행
     */
    abstract protected void fin();

    /**
     * 누군가를 공격할 떄 호출하는 메서드
     * 공격할 때 속성 데미지는 자동으로 계산된 후 {@link ElementDamageRange} 객체로 감싸져서 공격대상의 {@link Entity#attackedBy(ElementDamageRange, Entity)} 메서드를 호출시키는 데 사용됨
     * 
     * @param <T2> <b>to</b>의 타입
     * @param to 공격할 대상. {@link Entity}와 {@link Damageable}의 하위 클래스.
     */
    public abstract <T2 extends Entity & Damageable> void attack(T2 to);

    /**
     * 해당 엔티티의 체력이 0이하 이거나 특별한 원인으로 인해 사망할때 실행되는 메서드
     */
    public abstract void kill();

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level; 
    }

    /**
     * 엔티티의 스탯을 설정하게 해주는 setter 메서드
     * 
     * @param stats 설정하고자 하는 스탯
     * @param value 설정하고자 하는 값
     * @return 해당 객체 반환
     */
    public void setStat(LylacPlayerStatContainer.LylacPlayerStats stats, int value) {
        try {
            Field field = this.getClass().getDeclaredField(stats.name());
            field.setAccessible(true);
            field.setInt(this, value);
            field.setAccessible(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

        /**
     * 
     * @param stats 엔티티의 스탯에서 가져오고 싶은 스탯
     * @return 해당 스탯과 이름이 같은 필드 값을 리턴
     * @throws NoSuchFieldException 설정하고자 하는 스탯이 해당 필드에 존재하지 않을 때 발생하는 예외
     * @throws IllegalAccessException 필드 접근을 하지 못할 때 발생하는 예외.
     */
    public int getStat(LylacPlayerStatContainer.LylacPlayerStats stats) {
        try {
            Field field = this.getClass().getDeclaredField(stats.name());
            field.setAccessible(true);
            int V = field.getInt(this);
            field.setAccessible(false);
            return V;
        }
        catch(NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
