package net.kamilereon.lylac.element;

import java.lang.reflect.Constructor;

import javax.lang.model.element.Element;

/**
 * 속성 데미지의 범위를 설정하는 클래스
 * 
 * @author kamilereon
 * @version 1.0.0
 */
public class ElementDamageRange implements Cloneable {
    
    private Range neutral = Range.set(0, 0);
    private Range earth = Range.set(0, 0);
    private Range water = Range.set(0, 0);
    private Range fire = Range.set(0, 0);
    private Range air = Range.set(0, 0);

    private ElementDamageRange() {

    }
    
    public static ElementDamageRange getElementDamageRange() {
        return new ElementDamageRange();
    }

    /**
     * {@link ElementDamageRange}의 객체를 복사하는 메서드
     */
    @Override
    public ElementDamageRange clone() {
        ElementDamageRange copyObject = ElementDamageRange.getElementDamageRange();

        copyObject.neutral = Range.set(this.neutral.min, this.neutral.max);
        copyObject.earth = Range.set(this.earth.min, this.earth.max);
        copyObject.water = Range.set(this.water.min, this.water.max);
        copyObject.fire = Range.set(this.fire.min, this.fire.max);
        copyObject.air = Range.set(this.air.min, this.air.max);

        return copyObject;
    }

    /**
     * 
     * @return 필드의 범위 조건에 만족하는 무작위 필드 값을 가진 {@link ElementDamage} 객체를 반환함
     */
    public ElementDamage pickRandomElementDamage() {
        int neutral = (int) this.neutral.getRandom();
        int earth = (int) this.earth.getRandom();
        int water = (int) this.water.getRandom();
        int fire = (int) this.fire.getRandom();
        int air = (int) this.air.getRandom();
        return new ElementDamage(neutral, earth, water, fire, air);
    }
    
    public Range getNeutral() {
        return neutral;
    }

    public ElementDamageRange setNeutral(Range neutral) {
        this.neutral = neutral;
        return this;
    }

    public Range getEarth() {
        return earth;
    }

    public ElementDamageRange setEarth(Range earth) {
        this.earth = earth;
        return this;
    }

    public Range getWater() {
        return water;
    }

    public ElementDamageRange setWater(Range water) {
        this.water = water;
        return this;
    }

    public Range getFire() {
        return fire;
    }

    public ElementDamageRange setFire(Range fire) {
        this.fire = fire;
        return this;
    }

    public Range getAir() {
        return air;
    }

    public ElementDamageRange setAir(Range air) {
        this.air = air;
        return this;
    }

    public enum ElementDamageType {
        neutral,
        earth,
        water,
        fire,
        air
    }

    public static class Range {
        int min = 0;
        int max = 0;

        private Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public static Range set(int min, int max) {
            return new Range(min, max);
        }

        public void multiply(double val) {
            this.min *= val;
            this.max *= val;
        }

        public double getRandom() {
            return Math.random() * ( max - min ) + min;
        }
    }

}
