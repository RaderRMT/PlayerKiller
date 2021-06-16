package fr.rader.playerkiller.items;

import fr.rader.playerkiller.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomItem {

    private Material material;
    private String name;
    private String[] lore;

    public CustomItem(Material material, String name, String... lore) {
        this.material = material;
        this.name = name;
        this.lore = lore;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String[] getLore() {
        return lore;
    }

    public ItemStack getItemStack() {
        return new ItemBuilder(material).setName(name).setLore(lore).toItemStack();
    }
}
