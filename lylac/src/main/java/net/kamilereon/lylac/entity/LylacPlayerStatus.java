package net.kamilereon.lylac.entity;

/**
 * 인게임 내에서의 플레이어 상태에 관해서 서술
 */
public enum LylacPlayerStatus {
    /**
     * 플레이어가 퀘스트 스토리 모드에서 특정 씬(Scene)을 관전하고 있을 때 나타나는 상태
     * <p>적대적 몹 및 플레이어에게서 데미지를 받지 않으며 어떠한 효과에도 대상이 되지 않는다</p>.
     * <p>명령어는 사용 가능하다</p>
     */
    QUEST_SPECTATING, 
    /**
     * 플레이어가 보스, 레이드, 던전 등에서 사망하였을 때 또는 특정 씬(Scene)을 관전하고 있을 때 나타나는 상태
     * <p>적대적 몹 및 플레이어에게서 데미지를 받지 않으며 어떠한 효과에도 대상이 되지 않는다.</p>
     * <p>일부 명령어 또한 사용이 불가함.</p>
     */
    BOSS_SPECTATING,
    /**
     * 플레이어가 캐릭터를 바꾸는 도중에 나타나는 상태
     * <p></p>
     */
    CHARACTER_SWITCHING,
}
