package net.kamilereon.lylac.spell;

import net.kamilereon.lylac.Utils;
import net.kamilereon.lylac.entity.Player;

/**
 * 프로젝트: 라일락의 최상위 스펠 추상 클래스
 * <p>모든 스펠은 해당 클래스를 상속해야 한다.</p>
 * @author kamilereon
 * @version 1.0.0
 */
public abstract class Spell {

    protected final Player player;

    /**
     * 스펠 캐스팅 시간. 단위는 tick이다. 20tick = 1second
     * <p>{@link #cast()} 메서드가 실행되어 {@link #pre()}가 실행되고 {@link #spellCastingTime} 만큼의
     * 시간이 흐르면 {@link #emit()} 메서드가 실행된다.</p>
     * 
     * @see #pre()
     * @see #emit()
     * @see #cast()
     */
    protected int spellCastingTime = 100;

    public Spell(Player player) {
        this.player = player;
    }

    protected int getComputedSpellCastingTime() {
        return 1;
    }
    
    /**
     * 스펠 캐스팅을 시작 했을 때 발동하는 메서드
     */
    protected abstract void pre();

    /**
     * 스펠 캐스팅을 완료하고 방출할 때 발동하는 메서드
     * <p>{@link #pre()} 메서드가 실행된 후 {@link #spellCastingTime} 만큼의 시간이 흐르면 해당 메서드가 실행된다.</p>
     */
    protected abstract void emit();

    /**
     * 해당 스펠을 발동하는 메서드
     */
    public void cast() {
        pre();
        Utils.Scheduler.executeAfterTick(() -> emit(), getComputedSpellCastingTime());
    }
}
