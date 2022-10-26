package net.kamilereon.lylac.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        item.setItemMeta(itemMeta);
        return item;
    }

    public enum ItemRestrict {
        NORMAL,
        UNTRADABLE,
        QUEST,
    }

}
