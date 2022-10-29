package net.kamilereon.lylac.element;

import java.lang.reflect.Constructor;

import javax.lang.model.element.Element;

/**
 * 속성 데미지를 대표하는 클래스
 * 
 * @author kamilereon
 * @version 1.0.0
 */
public class ElementDamage implements Cloneable {
    
    private Range neutral = Range.set(0, 0);
    private Range earth = Range.set(0, 0);
    private Range water = Range.set(0, 0);
    private Range fire = Range.set(0, 0);
    private Range air = Range.set(0, 0);

    private ElementDamage() {

    }
    
    public static ElementDamage getElementDamage() {
        return new ElementDamage();
    }

    /**
     * {@link ElementDamage}의 객체를 복사하는 메서드
     */
    @Override
    public ElementDamage clone() {
        try {
            Class<ElementDamage> copyClass = ElementDamage.class;
            Constructor<ElementDamage> copyConstructor = copyClass.getDeclaredConstructor();
            copyConstructor.setAccessible(true);
            ElementDamage copyObject = copyConstructor.newInstance();
            copyConstructor.setAccessible(false);
            
            copyObject.neutral = Range.set(this.neutral.min, this.neutral.max);
            copyObject.earth = Range.set(this.earth.min, this.earth.max);
            copyObject.water = Range.set(this.water.min, this.water.max);
            copyObject.fire = Range.set(this.fire.min, this.fire.max);
            copyObject.air = Range.set(this.air.min, this.air.max);

            return copyObject;
        }
        catch(Exception e) {
            return null;
        }
    }
    
    public Range getNeutral() {
        return neutral;
    }

    public ElementDamage setNeutral(Range neutral) {
        this.neutral = neutral;
        return this;
    }

    public Range getEarth() {
        return earth;
    }

    public ElementDamage setEarth(Range earth) {
        this.earth = earth;
        return this;
    }

    public Range getWater() {
        return water;
    }

    public ElementDamage setWater(Range water) {
        this.water = water;
        return this;
    }

    public Range getFire() {
        return fire;
    }

    public ElementDamage setFire(Range fire) {
        this.fire = fire;
        return this;
    }

    public Range getAir() {
        return air;
    }

    public ElementDamage setAir(Range air) {
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
