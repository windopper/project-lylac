package net.kamilereon.lylac.entity;

import org.bukkit.entity.LivingEntity;

import net.kamilereon.lylac.element.ElementDamageRange;
import net.kamilereon.lylac.event.Cause.HealthMutateCause;

/**
 * {@link Entity} 가 피해를 받을 수 있음을 알려주는 인터페이스
 */
public interface Damageable {
    /**
     * 누군가에게 공격당했을 때 호출되는 메서드
     * 
     * @param <T2> <b>by</b>의 타입
     * @param eDamage 공격당했을 때 받은 속성 데미지 객체
     * @param by 나를 공격한 객체. {@link Entity}의 하위 클래스
     */
    public <T2 extends Entity> void attackedBy(ElementDamageRange eDamage, T2 by);

    /**
     * 해당 엔티티의 체력을 변화시키는 메서드
     * 
     * @param <T2> <b>by</b>의 타입
     * @param mutateValue 변화하는 값
     * @param cause 변화하는 이유
     * @param by 값을 변화시키는 원인 제공자. 없으면 {@code null} 값으로 설정 가능
     * 
     * @see HealthMutateCause
     * @see LylacPlayerHealthMutateEvent
     * @see LylacEntityHealthMutateEvent
     */
    public <T2 extends Entity> void mutateHealth(int mutateValue, HealthMutateCause cause, T2 by);

    public int getHealth();
}
