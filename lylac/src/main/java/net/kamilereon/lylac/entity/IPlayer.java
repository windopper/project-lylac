package net.kamilereon.lylac.entity;

import org.bukkit.entity.Player;

import net.kamilereon.lylac.item.artifact.ArtifactInventory;
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

    /**
     * 플레이어 데이터를 DB로부터 불러온다
     */
    public void loadData();

    /**
     * 플레이어 데이터와 캐릭터 데이터를 DB에 저장한다.
     */
    public void saveData();

    /**
     * 캐릭터 데이터를 DB로부터 불러온다.
     */
    public void loadCharacterData(String uuid);

    /**
     * 플레이어의 캐릭터를 바꾸는 상태로 진입시킨다.
     * <p>{@link net.kamilereon.lylac.entity.Player}의 모든 필드 값이 초기화 된다.</p>
     * @see net.kamilereon.lylac.entity.Player
     */
    public void enableCharacterSwitchMode();

    /**
     * 캐릭터를 생성한다.
     */
    public void createCharacter();

    /**
     * 캐릭터를 삭제한다.
     * @param uuid 삭제하고자 하는 캐릭터의 고유 식별 번호
     */
    public void removeCharacter(String uuid);

}
