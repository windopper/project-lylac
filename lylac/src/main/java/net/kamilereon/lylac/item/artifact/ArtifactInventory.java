package net.kamilereon.lylac.item.artifact;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.entity.Entity.EntityStats;
import net.kamilereon.lylac.item.artifact.Artifact.ArtifactType;

/**
 * 아티팩트 인벤토리 클래스. 해당 클래스에서 플레이어의 아티팩트 착용 여부를 관리하고 
 * 아티팩트들의 모든 스탯을 계산하여 플레이어의 능력치에 적용한다.
 * <p>{@link Player} 인스턴스의 final 필드로 저장되어있음</p>
 * @author kamilereon
 * @version 1.0.0
 * @see Player
 */
public class ArtifactInventory {

    private final Player inventoryHolder;

    /**
     * Key, Value 쌍으로 아티팩트가 저장되어 있음
     * 
     * <p>값 설정 및 불러오기는 {@link #setArtifact(ArtifactType, Artifact)}와 {@link #getArtifact(ArtifactType)} </p>
     * 
     * @see #setArtifact(ArtifactType, Artifact)
     * @see #getArtifact(ArtifactType)
     */
    private final Map<ArtifactType, Artifact> artifacts = new HashMap<ArtifactType, Artifact>() {{
        put(ArtifactType.HELMET, null);
        put(ArtifactType.CHESTPLATE, null);
        put(ArtifactType.LEGGINGS, null);
        put(ArtifactType.BOOTS, null);
        put(ArtifactType.EARING, null);
        put(ArtifactType.NECKLACE, null);
        put(ArtifactType.RING1, null);
        put(ArtifactType.RING2, null);
        put(ArtifactType.SCEPTOR, null);
    }};


    public ArtifactInventory(Player player) {
        this.inventoryHolder = player;
    }

    public void setArtifact(ArtifactType artifactType, Artifact artifact) {
        artifacts.replace(artifactType, artifact);
    }

    public Artifact getArtifact(ArtifactType artifactType) {
        return artifacts.get(artifactType);
    }

    /**
     * 보유한 모든 아티팩트들의 스탯을 합한 후, 해당 인벤토리 소유자의 스탯에 업데이트하는 메서드
     * <p>{@link Player#callWhenArtifactChanges()} 메서드에 의해서 자동으로 실행됨</p>
     * 
     * @see Player
     * @see ArtifactStat
     * @see Artifact
     */
    public void computeAllArtifactStatsAndUpdateToInventoryHoldersStat() {

        /**
         * 보유한 모든 아티팩트들의 스탯을 합하는 부분
         */

        ArtifactStat combinedArtifactStat = ArtifactStat.builder(); 
        Field[] fields = ArtifactStat.class.getDeclaredFields();
        // 모든 artifacts에 대하여 null 값을 제외한 나머지 값들을 getArtifactStat() 함수를 이용하여 ArtifactStat 객체로 반환

        ArtifactStat[] artifactStats = artifacts.values()
            .stream()
            .filter(artifact -> artifact != null) // null 값 제외
            .map(Artifact::getArtifactStat)
            .toArray(ArtifactStat[]::new); // 어레이로 변환
        
        // artifactStats 객체를 순회하며 대응하는 모든 필드의 값을 combined에 가산
        for(ArtifactStat artifactStat : artifactStats) {
            for(Field field : fields) {
                try {
                    // 필드 접근자가 private 이므로 접근 제한 풀기
                    field.setAccessible(true);
                    // 필드 이름
                    String K = field.getName();
                    // 필드 값
                    int V = (int) field.get(artifactStat);
                    // 대응하는 필드 꺼내기
                    Field combinedField = ArtifactStat.class.getDeclaredField(K);
                    combinedField.setAccessible(true);
                    // 대응하는 필드에 값 가산
                    combinedField.set(combinedArtifactStat, (int) combinedField.get(combinedArtifactStat) + V);
                    // 필드 잠그기
                    field.setAccessible(false);
                }
                catch(Exception e) {

                }
            }
        }

        /*
         * 플레이어 스탯에 적용하는 부분
         */

        for(Field field : fields) {
            try {
                field.setAccessible(true);
                String K = field.getName();
                // 아티팩트 스탯의 합 + 해당 능력치의 기본 값
                int V = (int) field.get(combinedArtifactStat) + EntityStats.valueOf(K).getDefaultValue();
                Field holdersField = inventoryHolder.getClass().getDeclaredField(K);
                holdersField.setAccessible(true);
                // 값 반영
                holdersField.setInt(inventoryHolder, V);
                holdersField.setAccessible(false);
                field.setAccessible(false);
            }
            catch(Exception e) {

            }
        }
    }
}