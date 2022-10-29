package net.kamilereon.lylac.entity;

import org.bukkit.entity.LivingEntity;

import net.kamilereon.lylac.element.ElementDamage;

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
    public abstract <T2 extends Entity<? extends LivingEntity>> void attackedBy(ElementDamage eDamage, T2 by);
}
