package net.kamilereon.lylac.entity;

import org.bukkit.entity.Player;

import net.kamilereon.lylac.item.artifact.ArtifactInventory;
import net.kamilereon.lylac.item.artifact.ArtifactStat;
import net.kamilereon.lylac.permission.LylacPlayerPermission;
import net.kamilereon.lylac.permission.LylacPlayerPermission.LylacPlayerPermissionType;
import net.kamilereon.lylac.quest.LylacQuest.LylacQuestList;
import net.kamilereon.lylac.spell.SpellInventory;
import net.kamilereon.lylac.spell.SpellTechInventory;

public interface IPlayer {

    public Player getBukkitEntity();

    public ArtifactInventory getArtifactInventory();

    public SpellInventory getSpellInventory();

    public SpellTechInventory getSpellTechInventory();

    public LylacPlayerPermission getPermission();

    /**
     * 플레이어가 가진 {@link ArtifactInventory} 타입의 필드에서 모든 스탯을 합친 후 그 값을 플레이어 객체
     * 에 업데이트
     * 
     * <p>주로 플레이어가 가진 아티팩트에 변화가 일어날 때 실행된다.</p>
     *  
     * @see ArtifactInventory
     * @see ArtifactStat
     */
    public void computeAllArtifactStatsAndUpdate();

    /**
     * 플레이어가 가진 셉터 데미지와 스탯들을 계산하여 업데이트 한다.
     * 
     * <p>주로 플레이어가 가진 아티팩트에 변화가 일어날 떄 실행된다.</p>
     */
    public void computeElementDamageRangeAndUpdate();

    /**
     * 해당 퀘스트를 시작한다.
     * <p>{@link LylacPlayerPermissionType#QUEST_START}의 권한이 있어야 실행 가능</p> 
     * 
     * @param quest 시작하고자 하는 퀘스트
     */
    public void startQuest(LylacQuestList quest);

    /**
     * 해당 퀘스트를 다음 단계로 진행한다.
     * <p>{@link LylacPlayerPermissionType#QUEST_PROGRESS}의 권한이 있어야 실행 가능</p> 
     * 
     * @param quest 다음으로 진행하고자 하는 퀘스트
     */
    public void progressQuest(LylacQuestList quest);

    /**
     * 해당 퀘스트를 완료한다.
     * <p>{@link LylacPlayerPermissionType#QUEST_FINISH}의 권한이 있어야 실행 가능</p> 
     * 
     * @param quest
     */
    public void completeQuest(LylacQuestList quest);

}
