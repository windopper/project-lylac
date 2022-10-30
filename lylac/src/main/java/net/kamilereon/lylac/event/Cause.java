package net.kamilereon.lylac.event;

public class Cause {
    /**
    * 체력이 변하게 된 원인
    * 
    * <p>{@link HealthMutateCause#ENVIRONMENT} 낙하데미지, 용암데미지, 끼임데미지 등 환경에 의한 데미지</p>
    * <p>{@link HealthMutateCause#NATURAL} 자연회복, 자연피해등 시간이 지나면 저절로 변화하는 체력에 대한 원인
    * <p>{@link HealthMutateCause#SPELL} 스펠로 인한 회복, 피해등</p>
    * <p>{@link HealthMutateCause#MELEE} 물리적인 피해, 즉 몬스터의 공격</p>
    * <p>{@link HealthMutateCause#UNKNOWN} 출처를 알 수 없을 때</p>
    */
    public enum HealthMutateCause {
        ENVIRONMENT,
        NATURAL,
        SPELL,
        MELEE,
        UNKNOWN
    }

    /**
     * 마나가 변하게 된 원인
     * 
     * <p>{@link ManaMutateCause#NATURAL} 자연 회복</p>
     * <p>{@link ManaMutateCause#USE} 마나를 스펠 캐스팅 할 때 사용할 때</p>
     * <p>{@link ManaMutateCause#SPELL} 스펠 효과를 인하여 마나가 변화할 때</p>
     * <p>{@link ManaMutateCause#UNKNOWN} 명시한 원인 외의 다른 것</p>
     */
    public enum ManaMutateCause {
        NATURAL,
        USE,
        SPELL,
        UNKNOWN
    }

    public enum XpMutateCause {
        MOB,
        QUEST,
        UNKNOWN,
    }
}
