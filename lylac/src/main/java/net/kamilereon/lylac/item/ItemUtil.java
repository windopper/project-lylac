package net.kamilereon.lylac.item;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.google.gson.JsonObject;

import net.kamilereon.lylac.Lylac;

public class ItemUtil {
    private static UUID getUUIDFromItemPersistentDataContainer(ItemMeta itemMeta) {
        return itemMeta.getPersistentDataContainer().get(NamespacedKey.fromString("uuid", Lylac.lylacPlugin), UUIDTagType.UUID);
    }
    private static <T, Z> boolean checkValueFromPersistentDataContainer(ItemMeta itemMeta, String key, PersistentDataType<T, Z> persistentDataType) {
        return itemMeta.getPersistentDataContainer().has(NamespacedKey.fromString(key, Lylac.lylacPlugin), persistentDataType);
    }
    private static <T, Z> void setValueToPersistentDataContainer(ItemMeta itemMeta, String key, PersistentDataType<T, Z> persistentDataType, Z value) {
        itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString(key, Lylac.lylacPlugin), persistentDataType, value);
    } 
    private static <T, Z> Z getValueFromPersistentDataContainer(ItemMeta itemMeta, String key, PersistentDataType<T, Z> persistentDataType) {
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
        maxMana(PersistentDataType.INTEGER),
        maxManaIncRate(PersistentDataType.INTEGER),
        manaRegenRate(PersistentDataType.INTEGER),
        manaConsumptionRate(PersistentDataType.INTEGER),
        maxHealth(PersistentDataType.INTEGER),
        maxHealthIncRate(PersistentDataType.INTEGER),
        healthRegenRate(PersistentDataType.INTEGER),
        spellCastingSpeed(PersistentDataType.INTEGER),
        speedWhileSpellCasting(PersistentDataType.INTEGER),
        spellResistance(PersistentDataType.INTEGER),
        spellAmplificationRate(PersistentDataType.INTEGER);

        PersistentDataType type;

        GeneratedItemModelField(PersistentDataType type) {
            this.type = type;
        }

        PersistentDataType getType() { return this.type; }

        Class getPrimitiveType() { return this.type.getPrimitiveType(); }
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
