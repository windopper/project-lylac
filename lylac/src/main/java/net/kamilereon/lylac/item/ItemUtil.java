package net.kamilereon.lylac.item;

import java.util.UUID;

import org.bson.Document;
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
    public static ItemStack getItemStackFromDocument(Document genItemObject, Document rootItemObject) {
        ItemStack item = LylacItem.builder()
        .setName(genItemObject.getString("name"))
        .setMaterial(Material.getMaterial(rootItemObject.getString("material")))
        .build();
        ItemMeta itemMeta = item.getItemMeta();
        for(LylacItem.GeneratedItemModelField field : LylacItem.GeneratedItemModelField.values()) {
            if(field.getType() == PersistentDataType.STRING) {
                setValueToPersistentDataContainer(itemMeta, field.name(), field.getType(), genItemObject.getString(field.name()));
            }
            else if(field.getType() == PersistentDataType.INTEGER) {
                setValueToPersistentDataContainer(itemMeta, field.name(), field.getType(), genItemObject.getInteger(field.name()));
            }
        }
        return item;
    }
}
