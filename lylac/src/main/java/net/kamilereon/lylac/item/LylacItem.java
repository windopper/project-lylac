package net.kamilereon.lylac.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

/**
 * 주어진 데이터를 받아 라일락 아이템으로 만들어 주는 클래스
 * <p>빌더 패턴으로 데이터를 받아 <code>build()</code> 메서드를 통해 <code>ItemStack</code>
 *  형식으로 뱉어주는 클래스 </p>
 * 
 */
public class LylacItem {

    /**
     * 
     */
    private String name = "";
    private Material material;
    private String lore = "";
    private String itemType = "";
    private int customModelData = 0;

    public static LylacItem builder() {
        return new LylacItem();
    }
    public LylacItem setName(String name) {
        this.name = name;
        return this;
    }
    public LylacItem setMaterial(Material material) { this.material = material; return this; }
    
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        return itemStack;
    }

    /**
     * RootItemModel를 기반으로 생성된 아이템의 필드
     * <p><code>PersistentDataType.INTEGER</code>를 기반으로 하는 필드는 범위가 0-100 사이임.</p>
     * <p>해당 필드들은 RootItemModel의 동일 이름의 필드 값으로 부터 값을 구해야 함</p>
     */
    public enum GeneratedItemModelField {
    
        uuid(PersistentDataType.STRING),
        name(PersistentDataType.STRING),
        createdAt(PersistentDataType.STRING),
        createdBy(PersistentDataType.STRING),

        maxMana(PersistentDataType.INTEGER, true),
        maxManaIncRate(PersistentDataType.INTEGER, true),
        manaRegenRate(PersistentDataType.INTEGER, true),
        manaConsumptionRate(PersistentDataType.INTEGER, true),
        maxHealth(PersistentDataType.INTEGER, true),
        maxHealthIncRate(PersistentDataType.INTEGER, true),
        healthRegenRate(PersistentDataType.INTEGER, true),
        spellCastingSpeed(PersistentDataType.INTEGER, true),
        speedWhileSpellCasting(PersistentDataType.INTEGER, true),
        spellResistance(PersistentDataType.INTEGER, true),
        spellAmplificationRate(PersistentDataType.INTEGER, true);
    
        PersistentDataType type;
        // 스탯 계산 될 때 사용하는 필드인지?
        boolean shouldComputedAsStat = false;
    
        GeneratedItemModelField(PersistentDataType type) {
            this.type = type;
        }
    
        GeneratedItemModelField(PersistentDataType type, boolean shouldComputedAsStat) {
            this.type = type;
            this.shouldComputedAsStat = shouldComputedAsStat;
        }
    
        PersistentDataType getType() { return this.type; }
    
        Class getPrimitiveType() { return this.type.getPrimitiveType(); }
    
        /**
         * 플레이어 능력치를 계산할 때 실질적으로 필요한 스탯들 가져오기
         * @return GeneratedItemModelField을 멤버로 가지는 리스트 반환
         */
        public static List<GeneratedItemModelField> getStatsField() {
            List<GeneratedItemModelField> list = new ArrayList<>();
            for(GeneratedItemModelField field : GeneratedItemModelField.values()) {
                if(!field.shouldComputedAsStat) continue;
                list.add(field);
            }
            return list;
        }
    }

    public enum RootItemModelField {
        listId,
        name,
        lore,
        isArtifact,
        itemType,
        restriction,
        material,
        customModelData,
        damage,
        maxMana,
        maxManaIncRate,
        manaRegenRate,
        manaConsumptionRate,
        maxHealth,
        maxHealthIncRate,
        healthRegenRate,
        spellCastingSpeed,
        speedWhileSpellCasting,
        spellResistance,
        meleeResistance,
        requireQuest,
        requireLevel,
        isGrowing,
        hiddenAbility,
        spellTechs,
        partsSlot
    }
}
