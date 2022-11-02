package net.kamilereon.lylac.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * 
 * @param name
 * @return
 */
public class LylacItem {

    private String name = "";
    private Material material;

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
}
