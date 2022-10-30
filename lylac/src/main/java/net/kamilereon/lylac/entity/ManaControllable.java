package net.kamilereon.lylac.entity;

import net.kamilereon.lylac.event.Cause.ManaMutateCause;

/**
 * 마나를 다룰 수 있고 스펠을 사용가능하게 함
 */
public interface ManaControllable {

    /**
     * 보유 마나를 변화시키는 메서드
     * 
     * <p>변화시키고자 하는 마나와 현재 마나의 합이 0 미만이 되면 0으로 자동 조정</p>
     * 
     * @param <T> <b>by</b>의 타입
     * @param mutateValue 변화하는 값
     * @param cause 변화하는 이유
     * @param by 변화하게된 원인 제공자
     */
    public <T extends Entity> void mutateMana(int mutateValue, ManaMutateCause cause, T by);

    public int setMana(int mana);

    public int getMana();

    /**
     * 스펠을 시전한다.
     * 
     * @param spellInventoryPosition 스펠 인벤토리에 있는 스펠 위치
     */
    public void castSpell(int spellInventoryPosition);
}
