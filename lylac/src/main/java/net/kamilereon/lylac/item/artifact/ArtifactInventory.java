package net.kamilereon.lylac.item.artifact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.item.ItemUtil;
import net.kamilereon.lylac.item.LylacItem;

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
    private final Map<ArtifactType, ItemStack> artifacts = new HashMap<ArtifactType, ItemStack>() {{
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

    public void setArtifact(ArtifactType artifactType, ItemStack artifact) {
        artifacts.replace(artifactType, artifact);
    }

    public ItemStack getArtifact(ArtifactType artifactType) {
        return artifacts.get(artifactType);
    }

    public void removeArtifact(ArtifactType artifactType) {
        artifacts.replace(artifactType, null);
    }

    /**
     * 보유한 모든 아티팩트들의 스탯을 합한 후, 해당 인벤토리 소유자의 스탯에 업데이트하는 메서드
     * <p>{@link Player#callWhenArtifactChanges()} 메서드에 의해서 자동으로 실행됨</p>
     * 
     * @see Player
     * @see ArtifactStat
     * @see Artifact
     */
    public Map<LylacItem.GeneratedItemModelField, Integer> combineAllArtifactStats() {

        /**
         * 보유한 모든 아티팩트들의 스탯을 합하는 부분
         */
        Map<LylacItem.GeneratedItemModelField, Integer> combinedStat = new HashMap<>();
        List<LylacItem.GeneratedItemModelField> fields = LylacItem.GeneratedItemModelField.getStatsField();

        // 값 초기화
        for(LylacItem.GeneratedItemModelField field : fields) {
            combinedStat.put(field, 0);
        }


        // 아티팩트들을 순회하며 combinedStat 에 값 계산
        for(ItemStack itemStack : artifacts.values()) {
            // 아티팩트가 장착되어 있지 않다면 건너뛰기
            if(itemStack == null) continue;
            ItemMeta itemMeta = itemStack.getItemMeta();
            for(LylacItem.GeneratedItemModelField field : fields) {
                int value = ItemUtil.getValueFromPersistentDataContainer(itemMeta, field.name(), PersistentDataType.INTEGER);
                // 값이 없다면 건너뛰기
                if(value == 0) continue;
                combinedStat.replace(field, combinedStat.get(field) + value);
            }
        }

        return combinedStat;
    }

    public enum ArtifactType {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        NECKLACE,
        EARING,
        RING1,
        RING2,
        SCEPTOR,
    }
}