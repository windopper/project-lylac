package net.kamilereon.lylac.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.kamilereon.lylac.Lylac;

public class Item {

    private String name = "item";
    private final UUID uuid;
    private final Material material;
    private int customModelData = 0;

    private ItemRestrict itemRestrict = ItemRestrict.NORMAL;

    private final List<String> lore = new ArrayList<>();
    protected Item(UUID uuid, Material material) {
        this.uuid = uuid;
        this.material = material;
    }

    public static Item builder(UUID uuid, Material material) {
        return new Item(uuid, material);
    }

    public Item addLore(String val) {
        this.lore.add(val);
        return this;
    }

    public Item setName(String val) {
        this.name = val;
        return this;
    }

    public Item setCustomModelData(int val) {
        this.customModelData = val;
        return this;
    }

    /**
     * @param itemRestrict 아이템에 대한 제한 사항
     * @return 해당 객체 재반환
     */
    public Item setItemRestrict(ItemRestrict itemRestrict) {
        this.itemRestrict = itemRestrict;
        return this;
    }

    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(this.lore);
        itemMeta.setCustomModelData(this.customModelData);
        ItemUtil.setValueToPersistentDataContainer(itemMeta, "uuid", UUIDTagType.UUID, uuid);
        item.setItemMeta(itemMeta);
        return item;
    }

    public enum ItemRestrict {
        NORMAL,
        UNTRADABLE,
        QUEST,
    }

    public static class ItemUtil {
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
    }
}
