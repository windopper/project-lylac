package net.kamilereon.lylac.spell;

import org.bukkit.scheduler.BukkitTask;

import net.kamilereon.lylac.Utils;
import net.kamilereon.lylac.entity.Player;

/**
 * 플레이어 캐스팅 커맨드를 보여주는 클래스
 */
public class CastingCommand {

    private final Player player;
    /**
     * 스펠 순서 
     * <p>예1) R - L - R</p>
     * <p>예2) R - L - L</p>
     */
    private final CastingCommandCode[] commands = { null, null, null };

    private BukkitTask waitForCommandsClear = null;

    /**
     * 2번쨰 스펠 슬롯 활성화
     * 
     * <p>플레이어 조작키가 기본 값으로 설정되어 있다면 F키를 통해 활성화 가능</p>
     * 값 전환은 {@link #disableNextSlot()} 또는 {@link #enableNextSlot()} 참조
     * @see #disableNextSlot()
     * @see #enableNextSlot()
     */
    private boolean nextSlotState = false;

    public CastingCommand(Player player) {
        this.player = player;
    }

    /**
     * F (손 바꾸기 버튼) 클릭 시 {@link #nextSlotState} 활성화
     */
    public void enableNextSlot() {
        this.nextSlotState = true;
    }

    /**
     * F (손 바꾸기 버튼) 클릭 시 {@link #nextSlotState} 비활성화
     */
    public void disableNextSlot() {
        this.nextSlotState = false;
    }

    /**
     * 
     * @param castingCommandCode 우클릭 시 {@link CastingCommandCode.R} 값 대입
     * <p>좌클릭 시 {@link CastingCommandCode.L} 값 대입</p>
     * <p>해당 메서드를 호출하면 캐스팅 커맨드가 추가됨</p>
     * {@link #commands}의 값이 모두 null이 아니게 되면 {@link #castSpell()} 메서드 실행 후 {@link #commands} 배열 초기화
     *  @see #castSpell()
     */
    public void attachCastingCommandCode(CastingCommandCode castingCommandCode) {
        if(this.waitForCommandsClear != null) {
            this.waitForCommandsClear.cancel();
            this.waitForCommandsClear = null;
        }
        for(int i=0; i<commands.length; i++) {
            if(commands[i] == null) {
                commands[i] = castingCommandCode;

                // 1.5초 시간 지난 후 commands 초기화
                this.waitForCommandsClear = Utils.Scheduler.executeAfterTick(() -> resetCommands(), 30);
                return;
            }
        }
        castSpell();
    }

    /**
     * <b>{@link #attachCastingCommandCode(CastingCommandCode)} 에 의해 실행 되는 메서드</b>
     * <p>{@link #commands}에 있는 값에 따라 사용되는 스펠이 달라짐</p>
     * <p></p>
     * @see SpellInventory
     */
    public void castSpell() {
        int spellInventoryPosition = Helper.getSpellInventoryPosition(nextSlotState, commands);
        player.castSpell(spellInventoryPosition);
        
        disableNextSlot();
        resetCommands();
    }

    /**
     * {@link #castSpell()} 이 실행되거나 {@link #attachCastingCommandCode(CastingCommandCode)} 가 실행된 후 일정 시간이 지나면
     * 실행되는 메서드
     * <hr/>
     * {@link #commands} 의 모든 값을 null 값으로 만든다.
     */
    public void resetCommands() {
        for(int i=0; i<commands.length; i++) {
            commands[i] = null;
        }
    }

    public enum CastingCommandCode {
        R,
        L
    }

    private static class Helper {
        static int getSpellInventoryPosition(boolean nextSlotState, CastingCommandCode ...commands) {
            
            int posRow = 0;
            int posColumn = 0;
            if(nextSlotState == true) posColumn = 4;
            
            if(commands[1] == CastingCommandCode.R) {
                if(commands[2] == CastingCommandCode.R) posRow = 1;
                else posRow = 3;
            }
            else {
                if(commands[2] == CastingCommandCode.R) posRow = 0;
                else posRow = 2;
            }

            return posRow + posColumn;
        }
    }
}
