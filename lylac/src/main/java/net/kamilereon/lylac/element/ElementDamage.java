package net.kamilereon.lylac.element;

/**
 * 정해진 속성 데미지를 저장하는 클래스 입니다.
 */
public class ElementDamage {

    private final int neutral;
    private final int earth;
    private final int water;
    private final int fire;
    private final int air;

    public ElementDamage(int neutral, int earth, int water, int fire, int air) {
        this.neutral = neutral;
        this.earth = earth;
        this.water = water;
        this.fire = fire;
        this.air = air;
    }

    public int getTotalDamage() {
        return neutral + earth + water + fire + air;
    }
}
