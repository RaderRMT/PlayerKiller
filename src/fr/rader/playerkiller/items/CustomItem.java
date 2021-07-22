package fr.rader.playerkiller.items;

import fr.rader.playerkiller.Main;
import fr.rader.playerkiller.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CustomItem {

    private final String type;

    private Material material;
    private String name;
    private List<String> lore;

    public CustomItem(String type) {
        this.type = type;

        getItemDataFromConfig();
    }

    private void getItemDataFromConfig() {
        FileConfiguration config = Main.getInstance().getCustomItemConfig().getConfig();

        material = Material.getMaterial(config.getString(type + ".material"));
        name = config.getString(type + ".name");
        lore = config.getStringList(type + ".lore");
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public ItemStack getItemStack() {
        return new ItemBuilder(material).setName(name).setLore(lore).toItemStack();
    }

    public boolean equals(CustomItem item) {
        if(this == item) {
            return true;
        }

        if(item.getName().equals(this.getName())) {
            return true;
        }

        return (this.getMaterial().equals(item.getMaterial()) &&
            this.getType().equals(item.getType()) &&
            this.getLore().equals(item.getLore()));
    }

    public String getType() {
        return type;
    }
}
