package net.kamilereon.lylac.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.google.gson.JsonObject;

import net.kamilereon.lylac.Lylac;

public class ItemUtil {
    public static UUID getUUIDFromItemPersistentDataContainer(ItemMeta itemMeta) {
        return itemMeta.getPersistentDataContainer().get(NamespacedKey.fromString("uuid", Lylac.lylacPlugin), UUIDTagType.UUID);
    }
    public static <T, Z> boolean checkValueFromPersistentDataContainer(ItemMeta itemMeta, String key, PersistentDataType<T, Z> persistentDataType) {
        return itemMeta.getPersistentDataContainer().has(NamespacedKey.fromString(key, Lylac.lylacPlugin), persistentDataType);
    }
    public static <T, Z> void setValueToPersistentDataContainer(ItemMeta itemMeta, String key, PersistentDataType<T, Z> persistentDataType, Z value) {
        itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString(key, Lylac.lylacPlugin), persistentDataType, value);
    } 
    public static <T, Z> Z getValueFromPersistentDataContainer(ItemMeta itemMeta, String key, PersistentDataType<T, Z> persistentDataType) {
        return itemMeta.getPersistentDataContainer().get(NamespacedKey.fromString(key, Lylac.lylacPlugin), persistentDataType);
    }

    /**
     * JSON 형식의 아이템 정보를 라일락 템플릿에 맞는 마인크래프트 아이템으로 바꿈
     * 
     * @param genItemObject 생성된 아이템 JSON 형식 오브젝트
     * @param rootItemObject 등록된 아이템 JSON 형식 오브젝트
     * @return 버킷 아이템 리턴
     */
    public static ItemStack getItemStackFromJSONObject(JsonObject genItemObject, JsonObject rootItemObject) {
        ItemStack item = LylacItem.builder()
        .setName(genItemObject.get("name").getAsString())
        .setMaterial(Material.getMaterial(rootItemObject.get("material").getAsString()))
        .build();
        ItemMeta itemMeta = item.getItemMeta();
        for(GeneratedItemModelField field : GeneratedItemModelField.values()) {
            if(field.getType() == PersistentDataType.STRING) {
                setValueToPersistentDataContainer(itemMeta, field.name(), field.getType(), genItemObject.get(field.name()).getAsString());
            }
            else if(field.getType() == PersistentDataType.INTEGER) {
                setValueToPersistentDataContainer(itemMeta, field.name(), field.getType(), genItemObject.get(field.name()).getAsInt());
            }
        }
        return item;
    }

    /**
     * 
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
